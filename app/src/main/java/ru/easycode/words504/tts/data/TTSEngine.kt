package ru.easycode.words504.tts.data

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import ru.easycode.words504.tts.domain.TTSErrorsFactory
import java.util.Locale

interface TTSEngine : TTSControl {

    fun init(initCallback: TextToSpeech.OnInitListener)

    fun addObserver(observer: TTSObserver)

    fun speak(phrases: List<String>)

    class Base(
        private val context: Context,
        private val observersStorage: ObserversStorage<TTSObserver>,
        private val controlObserversStorage: ObserversStorage<TTSControlObserver>,
        private val errorFactory: TTSErrorsFactory
    ) : TTSEngine {

        private lateinit var tts: TextToSpeech

        private val wordsQueue: MutableList<String> = ArrayList()
        private var isPaused: Boolean = false

        override fun addObserver(observer: TTSObserver) {
            observersStorage.add(observer)
        }

        override fun init(initCallback: TextToSpeech.OnInitListener) {
            tts = TextToSpeech(context, initCallback)
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
                    wordsQueue.removeFirst()
                    observersStorage.notify { it.finished(utteranceId) }
                    ttsSpeak()
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
            wordsQueue.clear()
            wordsQueue.addAll(phrases)
            ttsSpeak()
        }

        override fun pause() {
            if (!isPaused) {
                isPaused = true
                tts.stop()
            }
        }

        override fun resume() {
            if (isPaused) {
                ttsSpeak()
            }
        }

        private fun ttsSpeak() {
            wordsQueue.firstOrNull()?.let {
                tts.speak(it, TextToSpeech.QUEUE_FLUSH, null, it)
            }
        }
    }
}
