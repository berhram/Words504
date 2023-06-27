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
    ) :
        TTSEngine {

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
                    if (interrupted && !isPaused) {
                        controlObserversStorage.notify { it.stopped() }
                    } else {
                        controlObserversStorage.notify { it.paused() }
                    }
                }

                override fun onStart(utteranceId: String) {
                    observersStorage.notify { it.started(utteranceId) }
                    controlObserversStorage.notify { it.resumed() }
                }

                override fun onDone(utteranceId: String) {
                    wordsQueue.removeLast()
                    observersStorage.notify { it.finished(utteranceId) }
                    if (!isPaused) {
                        ttsSpeak(wordsQueue.last())
                    }
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
            ttsSpeak(wordsQueue.last())
        }

        override fun pause() {
            if (tts.isSpeaking) {
                isPaused = true
                tts.stop()
            }
        }


        override fun resume() {
            if (isPaused) {
                isPaused = false
                tts.speak(wordsQueue.last(), TextToSpeech.QUEUE_FLUSH, null, wordsQueue.last())
            }
        }

        override fun stop() {
            tts.stop()
            wordsQueue.clear()
        }

        private fun ttsSpeak(text: String) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, text)
        }
    }
}
