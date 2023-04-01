package ru.easycode.words504.recognition.presentation


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
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import ru.easycode.words504.R
import ru.easycode.words504.recognition.data.SpeechRecognizerCallback
import ru.easycode.words504.recognition.data.SpeechRecognizerEngine
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

//class TestVoiceRecognitionViewModel : SpeachViewModel()

/*abstract class SpeechViewModel(private val speechRecognizerEngine: SpeechRecognizerEngine,
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
}*/