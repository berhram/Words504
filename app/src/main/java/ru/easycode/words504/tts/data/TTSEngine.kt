package ru.easycode.words504.tts.data

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale

interface TTSEngine {

    fun init(initCallback: TextToSpeech.OnInitListener, ttsCallback: TTSCallback)
    fun speak(phrase: String)

    class Base(private val context: Context) : TTSEngine {

        private lateinit var tts: TextToSpeech
        private lateinit var callback: TTSCallback

        override fun init(initCallback: TextToSpeech.OnInitListener, ttsCallback: TTSCallback) {
            tts = TextToSpeech(context, initCallback)
            tts.language = Locale.ENGLISH
            callback = ttsCallback
            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String) = Unit

                override fun onDone(utteranceId: String) {
                    ttsCallback.finished()
                }

                override fun onError(utteranceId: String) = Unit
            })
        }

        override fun speak(phrase: String) {
            tts.speak(phrase, TextToSpeech.QUEUE_FLUSH, null, phrase)
        }
    }
}
