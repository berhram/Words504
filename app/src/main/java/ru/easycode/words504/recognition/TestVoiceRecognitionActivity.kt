package ru.easycode.words504.recognition


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import ru.easycode.words504.R
import java.util.*

//todo 1. VM с SpeechRecognizerEngine
//todo 2. По примеру вынести пермишены в VM
//todo 3. touch listener по началу старт, по концу стоп
//todo 4. Вынести все в абстракнтую VM test

class TestVoiceRecognitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_voice_recognition)

        val speechRecognizerEngine = SpeechRecognizerEngine.Base(applicationContext)

        val textView = findViewById<TextView>(R.id.resultTextView)

        val callback = object : SpeechRecognizerCallback {
            override fun started() {
                textView.text = "Started"
            }

            override fun finished(result: String) {
                textView.text = result
            }

            override fun error(code: Int) {
                textView.text = code.toString()
            }
        }

        val button = findViewById<Button>(R.id.speakButton)
        button.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.RECORD_AUDIO
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    speechRecognizerEngine.start(callback)
                } else {
                    registerForActivityResult(
                        ActivityResultContracts.RequestPermission()
                    ) { isGranted: Boolean ->
                        if (isGranted) {
                            speechRecognizerEngine.start(callback)
                        } else {
                            textView.text = "Зря"
                        }
                    }.launch(
                        Manifest.permission.RECORD_AUDIO
                    )
                }
            } else {
                speechRecognizerEngine.start(callback)
            }
        }
    }

}

interface SpeechRecognizerCallback {
    fun started()
    fun finished(result: String)
    fun error(code: Int)//todo смаппить в домен
}

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

abstract class SimpleRecognitionListener : RecognitionListener {
    override fun onReadyForSpeech(params: Bundle?) = Unit

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(rmsdB: Float) = Unit

    override fun onBufferReceived(buffer: ByteArray?) = Unit

    override fun onEndOfSpeech() = Unit

    override fun onError(error: Int) = Unit

    override fun onResults(results: Bundle?) = Unit

    override fun onPartialResults(partialResults: Bundle?) = Unit

    override fun onEvent(eventType: Int, params: Bundle?) = Unit

}

class TestVoiceRecognitionViewModel : SpeachViewModel()

abstract class SpeachViewModel(private val speechRecognizerEngine: SpeechRecognizerEngine,
private val communication: RecognitionResultCommunication): ViewModel(){

    private val callback = object : SpeechRecognizerCallback {
        override fun started() {
            //communication put ... "Started"
        }

        override fun finished(result: String) {
            textView.text = result
        }

        override fun error(code: Int) {
            textView.text = code.toString()
        }
    }

    //fun start
    //stop
}

/**
 * checkout main
 * pull
 * checkout vrg-0001
 * on main merge in to current
 */