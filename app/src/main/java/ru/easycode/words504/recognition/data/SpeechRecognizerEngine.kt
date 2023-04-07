package ru.easycode.words504.recognition.data

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import java.util.*

interface SpeechRecognizerEngine {
    fun start(callback: SpeechRecognizerCallback)
    fun stop()

    class Base(context: Context) : SpeechRecognizerEngine {

        private val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)

        private val speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            .putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            .putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.ENGLISH.language
            )

        override fun start(callback: SpeechRecognizerCallback) {
            speechRecognizer.setRecognitionListener(object : SimpleRecognitionListener() {
                override fun onReadyForSpeech(params: Bundle?) {
                    callback.started()
                }

                override fun onError(error: Int) {
                    callback.error(error)
                }

                override fun onResults(results: Bundle?) {
                    val data: ArrayList<String> =
                        results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) as ArrayList<String>
                    callback.finished(data[0])
                }
            })

            speechRecognizer.startListening(speechRecognizerIntent)
        }

        override fun stop() {
            speechRecognizer.stopListening()
        }
    }
}

