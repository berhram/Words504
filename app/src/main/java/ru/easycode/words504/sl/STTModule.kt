package ru.easycode.words504.sl

import android.Manifest.permission.RECORD_AUDIO
import android.content.Context
import android.os.Build
import ru.easycode.words504.recognition.data.STTErrorsFactory
import ru.easycode.words504.recognition.data.SpeechRecognizerEngine
import ru.easycode.words504.recognition.domain.STTHandleError
import ru.easycode.words504.recognition.domain.ToSTTUiError
import ru.easycode.words504.recognition.presentation.HandlePermissionGranted
import ru.easycode.words504.recognition.presentation.PermissionCommunication
import ru.easycode.words504.recognition.presentation.RecognitionResultCommunication
import ru.easycode.words504.recognition.presentation.RequestPermission
import ru.easycode.words504.recognition.presentation.TestSTTViewModel

class STTModule(private val context: Context, private val core: CoreModule) :
    Module<TestSTTViewModel> {
    override fun viewModel(): TestSTTViewModel {
        var viewModel: HandlePermissionGranted = HandlePermissionGranted.Empty()
        val handlePermissionGranted = object : HandlePermissionGranted {
            override fun permissionCallback(granted: Boolean) {
                viewModel.permissionCallback(granted)
            }
        }
        val requestPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermission.AboveM(RECORD_AUDIO, handlePermissionGranted)
        } else {
            RequestPermission.UnderM(handlePermissionGranted)
        }

        viewModel = TestSTTViewModel(
            requestPermission,
            PermissionCommunication.Base(),
            RecognitionResultCommunication.Base(),
            SpeechRecognizerEngine.Base(context),
            core,
            STTHandleError(ToSTTUiError(core), STTErrorsFactory.Base())
        )
        return viewModel
    }
}
