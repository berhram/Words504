package ru.easycode.words504.tts.data

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.LinkedList
import java.util.Locale
import java.util.Queue

interface TTSEngine {

    fun init(initCallback: TextToSpeech.OnInitListener)

    fun addObserver(observer: TTSObserver)

    fun speak(phrases: List<String>)

    fun pause()

    fun resume()

    class Base(private val context: Context) : TTSEngine {

        private lateinit var tts: TextToSpeech
        private val observers: MutableList<TTSObserver> = mutableListOf()
        private val wordsQueue: Queue<String> = LinkedList()
        private var isPaused: Boolean = false

        override fun addObserver(observer: TTSObserver) {
            observers.add(observer)
        }

        override fun init(initCallback: TextToSpeech.OnInitListener) {
            tts = TextToSpeech(context, initCallback)
            tts.language = Locale.ENGLISH
            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStop(utteranceId: String?, interrupted: Boolean) {
                    super.onStop(utteranceId, interrupted)
                    if (interrupted && !isPaused) {
                        wordsQueue.offer(utteranceId)
                    }
                }

                override fun onStart(utteranceId: String) {
                    observers.forEach { it.started(utteranceId) }
                }

                override fun onDone(utteranceId: String) {
                    observers.forEach { it.finished(utteranceId) }
                    if (!isPaused) {
                        wordsQueue.poll()?.let {
                            speak(listOf(it))
                        }
                    }
                }

                override fun onError(utteranceId: String) = Unit
            })
        }

        override fun speak(phrases: List<String>) {
            isPaused = false
            tts.stop()
            wordsQueue.clear()
            wordsQueue.addAll(phrases)
            wordsQueue.poll()?.let {
                tts.speak(it, TextToSpeech.QUEUE_FLUSH, null, it)
            }
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
                wordsQueue.poll()?.let {
                    tts.speak(it, TextToSpeech.QUEUE_FLUSH, null, it)
                }
            }
        }
    }
}

