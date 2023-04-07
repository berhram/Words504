package ru.easycode.words504.recognition.presentation


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import ru.easycode.words504.R
import ru.easycode.words504.recognition.data.SpeechRecognizerEngine
import ru.easycode.words504.sl.ProvideViewModel

//todo 1. VM с SpeechRecognizerEngine
//todo 2. По примеру вынести пермишены в VM
//todo 3. touch listener по началу старт, по концу стоп
//todo 4. Вынести все в абстракнтую VM test

class TestVoiceRecognitionActivity : AppCompatActivity(), ProvideViewModel {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_voice_recognition)

        val viewModel = viewModel(TestSTTViewModel::class.java, this)

        val launcher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                viewModel.permissionCallback(isGranted)
            }

        val textView = findViewById<TextView>(R.id.resultTextView)

        val button = findViewById<Button>(R.id.speakButton)
        viewModel.init(savedInstanceState == null)
        button.setOnClickListener {
            viewModel.startRecord()
        }
        viewModel.observeRequestPermission(this){
            it.handle(this, launcher)
        }
        viewModel.observeRecognitionResult(this){
            textView.text = it.toString()
        }
    }

    /*override fun <T : ViewModel> viewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        (application as ProvideViewModel).viewModel(clazz, owner)*/
    //todo раскомментировать

    override fun <T : ViewModel> viewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T {

        var viewModel: TestSTTViewModel? = null
        val handlePermissionGranted = object : HandlePermissionGranted {
            override fun permissionCallback(granted: Boolean) {
                viewModel?.permissionCallback(granted)
            }
        }
        val requestPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            RequestPermission.AboveM(Manifest.permission.RECORD_AUDIO, handlePermissionGranted)
        else
            RequestPermission.UnderM(handlePermissionGranted)
        viewModel = TestSTTViewModel(
            requestPermission,
            PermissionCommunication.Base(),
            RecognitionResultCommunication.Base(),
            SpeechRecognizerEngine.Base(applicationContext)
        )
        return viewModel as T //todo in service locator
    }

}