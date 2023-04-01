package ru.easycode.words504.recognition.data

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import ru.easycode.words504.recognition.presentation.SpeechRecognizerCallback
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
                override fun onReadyForSpeech(p0: Bundle?) {
                    callback.started()
                }

                override fun onError(p0: Int) {
                    callback.error(p0)
                }

                override fun onResults(p0: Bundle?) {
                    val data: ArrayList<String> =
                        p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION) as ArrayList<String>
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

