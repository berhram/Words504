package ru.easycode.words504.tts.data

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import ru.easycode.words504.tts.domain.TTSErrorsFactory
import java.util.LinkedList
import java.util.Locale
import java.util.Queue

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

        private val wordsQueue: Queue<String> = LinkedList()
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
                        wordsQueue.offer(utteranceId)
                        // TODO надо расставить в правильных местах колбэки TTSControlObserver для панельки управления
                        controlObserversStorage.notify { it.stopped() }
                    } else {
                        // TODO надо расставить в правильных местах колбэки TTSControlObserver для панельки управления
                        controlObserversStorage.notify { it.paused() }
                    }
                }

                override fun onStart(utteranceId: String) {
                    observersStorage.notify { it.started(utteranceId) }
                    // TODO надо расставить в правильных местах колбэки TTSControlObserver для панельки управления
                    controlObserversStorage.notify { it.resumed() }
                }

                override fun onDone(utteranceId: String) {
                    observersStorage.notify { it.finished(utteranceId) }
                    if (!isPaused) {
                        wordsQueue.poll()?.let {
                            ttsSpeak(it)
                        }
                    }
                }

                // TODO не получилось эмулировать ошибку, у меня и без интернета читает
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
            wordsQueue.poll()?.let {
                ttsSpeak(it)
            }
        }

        override fun pause() {
            if (tts.isSpeaking) {
                isPaused = true
                tts.stop()
            }
        }

        // TODO Если во время чтения предложения поставить паузу, а потом возобновить, то читать начинает со следующего предложения
        override fun resume() {
            if (isPaused) {
                isPaused = false
                wordsQueue.poll()?.let {
                    tts.speak(it, TextToSpeech.QUEUE_FLUSH, null, it)
                }
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
