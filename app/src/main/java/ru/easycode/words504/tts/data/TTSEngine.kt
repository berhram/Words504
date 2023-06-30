package ru.easycode.words504.tts.data

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale
import ru.easycode.words504.tts.domain.TTSError
import ru.easycode.words504.tts.domain.TTSErrorsFactory

interface TTSEngine : TTSControl {

    fun init()

    fun addObserver(observer: TTSObserver)

    fun speak(phrases: List<String>)

    class Base(
        private val context: Context,
        private val observersStorage: ObserversStorage<TTSObserver>,
        private val controlObserversStorage: ObserversStorage<TTSControlObserver>,
        private val errorFactory: TTSErrorsFactory,
        private val queue: TTSQueue.Queue
    ) : TTSEngine, TTSQueue.Callback {

        private lateinit var tts: TextToSpeech

        private var isPaused: Boolean = false

        override fun addObserver(observer: TTSObserver) {
            observersStorage.add(observer)
        }

        override fun speak(data: String) {
            tts.speak(data, TextToSpeech.QUEUE_FLUSH, null, data)
        }

        override fun init() {
            queue.init(this)
            tts = TextToSpeech(context) { status ->
                if (status == TextToSpeech.ERROR) {
                    observersStorage.notify { it.error(TTSError.ErrorInit()) }
                }
            }
            tts.language = Locale.ENGLISH
            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStop(utteranceId: String?, interrupted: Boolean) {
                    super.onStop(utteranceId, interrupted)
                    controlObserversStorage.notify { it.paused() }
                }

                override fun onStart(utteranceId: String) {
                    if (isPaused) {
                        isPaused = false
                        controlObserversStorage.notify { it.resumed() }
                    }
                    observersStorage.notify { it.started(utteranceId) }
                }

                override fun onDone(utteranceId: String) {
                    queue.done()
                    observersStorage.notify { it.finished(utteranceId) }
                    queue.processNext()
                }

                @Deprecated("Deprecated in Java", ReplaceWith("Unit"))
                override fun onError(utteranceId: String) = Unit

                override fun onError(utteranceId: String, errorCode: Int) {
                    observersStorage.notify {
                        it.error(errorFactory.createError(errorCode))
                    }
                }
            })
        }

        override fun speak(phrases: List<String>) {
            isPaused = false
            tts.stop()
            queue.clear()
            queue.addAll(phrases)
            queue.processNext()
        }

        override fun switchPlayAndPause() {
            if (isPaused) {
                queue.processNext()
            } else {
                isPaused = true
                tts.stop()
            }
        }
    }
}
