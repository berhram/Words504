package ru.easycode.words504.recognition.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import ru.easycode.words504.R
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.recognition.data.SpeechRecognizerEngine
import ru.easycode.words504.recognition.domain.STTHandleError
import ru.easycode.words504.recognition.domain.ToSTTUiError
import ru.easycode.words504.sl.ProvideViewModel

//todo make abstract fragment with generic of viewModel in STTViewModel
/*abstract class STTFragment<T : STTViewModel> :
    Fragment() { //todo BaseFragment onCreate viewModel init

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val launcher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                viewModel.permissionCallback(isGranted)
            }

        viewModel.observeRequestPermission(this) {
            it.handle(this, launcher)
        }
    }
}*/

//todo  test fragment replace in main activity
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
        viewModel.observeRequestPermission(this) {
            it.handle(this, launcher)
        }
        viewModel.observeRecognitionResult(this) {
            it.show(textView)
        }
    }

    /*override fun <T : ViewModel> viewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        (application as ProvideViewModel).viewModel(clazz, owner)*/
    //todo раскомментировать

    override fun <T : ViewModel> viewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T {
        var viewModel: HandlePermissionGranted = HandlePermissionGranted.Empty()
        val handlePermissionGranted = object : HandlePermissionGranted {
            override fun permissionCallback(granted: Boolean) {
                viewModel.permissionCallback(granted)
            }
        }
        val requestPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            RequestPermission.AboveM(Manifest.permission.RECORD_AUDIO, handlePermissionGranted)
        else
            RequestPermission.UnderM(handlePermissionGranted)

        val manageResources = ManageResources.Base(applicationContext)
        viewModel = TestSTTViewModel(
            requestPermission,
            PermissionCommunication.Base(),
            RecognitionResultCommunication.Base(),
            SpeechRecognizerEngine.Base(applicationContext),
            manageResources,
            STTHandleError(ToSTTUiError(manageResources))
        )
        return viewModel as T //todo in service locator
    }
}