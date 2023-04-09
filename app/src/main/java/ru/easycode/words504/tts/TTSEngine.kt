package ru.easycode.words504.tts

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.speech.tts.UtteranceProgressListener
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue

interface TTSEngine {
    fun speak(phrases: List<String>)
    fun speak(phrase: String)
    fun stop()
    fun setEndOfSpeechListener(callback : (phrase: String) -> Unit)
    fun setPartialEndOfSpeechListener(callback : (phrase: String) -> Unit)
    fun setStartOfSpeechListener(callback : (phrase: String) -> Unit)

    class Base(context: Context, onInitListener: OnInitListener) : TTSEngine {

        private val tts: TextToSpeech
        private val queue: LinkedBlockingQueue<String> = LinkedBlockingQueue()
        private lateinit var startSpeechCallback : (phrase: String) -> Unit
        private lateinit var endSpeechCallback : (phrase: String) -> Unit
        private lateinit var partialEndSpeechCallback : (phrase: String) -> Unit

        private val utteranceProgressListener = object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                startSpeechCallback.invoke(utteranceId ?: "")
            }

            override fun onDone(utteranceId: String?) {
                queue.poll()
                (if (queue.size > 0) partialEndSpeechCallback else endSpeechCallback).invoke(utteranceId ?: "")
            }

            override fun onError(utteranceId: String?) {
            }

            override fun onStop(utteranceId: String?, interrupted: Boolean) {
                queue.clear()
                endSpeechCallback.invoke(utteranceId ?: "")
            }
        }

        init {
            tts = TextToSpeech(context, onInitListener)
            tts.setOnUtteranceProgressListener(utteranceProgressListener)
            tts.language = Locale.ENGLISH
        }

        override fun speak(phrases: List<String>) {
            stop()
            phrases.forEach {phrase ->
                if (phrase.isEmpty()) return
                queue.add(phrase)
                tts.speak(phrase, TextToSpeech.QUEUE_ADD, null, phrase)
            }
        }

        override fun speak(phrase: String) {
            stop()
            queue.add(phrase)
            tts.speak(phrase, TextToSpeech.QUEUE_ADD, null, phrase)
        }

        override fun stop() {
            tts.stop()
        }

        override fun setEndOfSpeechListener(callback: (phrase: String) -> Unit) {
            endSpeechCallback = callback
        }

        override fun setPartialEndOfSpeechListener(callback: (phrase: String) -> Unit) {
            partialEndSpeechCallback = callback
        }

        override fun setStartOfSpeechListener(callback: (phrase: String) -> Unit) {
            startSpeechCallback = callback
        }
    }
}