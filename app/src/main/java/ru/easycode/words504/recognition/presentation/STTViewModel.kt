package ru.easycode.words504.recognition.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.recognition.STTState
import ru.easycode.words504.recognition.data.SpeechRecognizerCallback
import ru.easycode.words504.recognition.data.SpeechRecognizerEngine

abstract class STTViewModel(
    private val requestPermission: RequestPermission,
    private val permissionCommunication: PermissionCommunication,
    private val recognitionResultCommunication: RecognitionResultCommunication.Mutable,
    private val speechRecognizerEngine: SpeechRecognizerEngine,
) : ViewModel(), Init, HandlePermissionGranted, ObserveSTT {

    override fun init(isFirstRun: Boolean) {
        permissionCommunication.map(requestPermission)
    }

    private val callback = object : SpeechRecognizerCallback {
        override fun started() {
            recognitionResultCommunication.map(STTState.Started("Started"))
        }

        override fun finished(result: String) {
            recognitionResultCommunication.map(STTState.Finished(result))
        }

        override fun error(code: Int) {
            //todo в какие строки из чисел?
            recognitionResultCommunication.map(STTState.Error(code.toString()))
        }
    }

    fun observeRequestPermission(owner: LifecycleOwner, observer: Observer<RequestPermission>) {
        permissionCommunication.observe(owner, observer)
    }

    fun startRecord() {
        speechRecognizerEngine.start(callback)
    }

    fun stopRecord() {
        speechRecognizerEngine.stop()
    }

    override fun observeRecognitionResult(owner: LifecycleOwner, observer: Observer<STTState>) {
        recognitionResultCommunication.observe(owner, observer)
    }
}

class TestSTTViewModel(
    requestPermission: RequestPermission,
    permissionCommunication: PermissionCommunication,
    recognitionResultCommunication: RecognitionResultCommunication.Mutable,
    speechRecognizerEngine: SpeechRecognizerEngine
) : STTViewModel(
    requestPermission,
    permissionCommunication,
    recognitionResultCommunication,
    speechRecognizerEngine
) {
    override fun permissionCallback(granted: Boolean) {
        if(granted){

        } else
        {
            //todo предупредить
        }
    }

}

interface Init {
    fun init(isFirstRun: Boolean)
}

interface PermissionCommunication : Communication.Mutable<RequestPermission> {

    class Base : Communication.Abstract<RequestPermission>(), PermissionCommunication
}

