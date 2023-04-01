package ru.easycode.words504.recognition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.easycode.words504.recognition.data.VoiceRecognition

class MainViewModel(
    val callbackLiveData: MutableLiveData<TTSState>,
    private val voiceRecognition: VoiceRecognition
) : ViewModel() {

    fun init() {}

    fun handleCallback(state: TTSState) {
        callbackLiveData.value = state
    }

    fun record() {
        voiceRecognition.record()
    }

    fun stopRecord() {
        voiceRecognition.stop()
    }

    fun setRecogniseText() {
        voiceRecognition.recogniseText()
    }

}