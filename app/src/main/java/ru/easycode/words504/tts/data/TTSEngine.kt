package ru.easycode.words504.tts.data

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale

interface TTSEngine {

    fun init(initCallback: TextToSpeech.OnInitListener)

    fun addObserver(observer: TTSObserver)

    fun speak(phrases: List<String>)

    class Base(private val context: Context) : TTSEngine {

        private lateinit var tts: TextToSpeech
        private val observers: MutableList<TTSObserver> = mutableListOf()

        override fun addObserver(observer: TTSObserver) {
            observers.add(observer)
        }

        override fun init(initCallback: TextToSpeech.OnInitListener) {
            tts = TextToSpeech(context, initCallback)
            tts.language = Locale.ENGLISH
            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {

                override fun onStart(utteranceId: String) {
                    observers.forEach { it.started(utteranceId) }
                }

                override fun onDone(utteranceId: String) {
                    observers.forEach { it.finished(utteranceId) }
                }

                override fun onError(utteranceId: String) = Unit
            })
        }

        override fun speak(phrases: List<String>) {
            tts.stop()
            phrases.forEach { phrase ->
                if (phrase.isEmpty()) return
                tts.speak(phrase, TextToSpeech.QUEUE_ADD, null, phrase)
            }
        }
    }
}
