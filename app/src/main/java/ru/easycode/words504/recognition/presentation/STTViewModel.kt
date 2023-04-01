package ru.easycode.words504.recognition.presentation

import androidx.lifecycle.ViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.recognition.data.VoiceRecognition

abstract class STTViewModel(
    private val recognitionResultCommunication: RecognitionResultCommunication,
    private val voiceRecognition: VoiceRecognition
) : ViewModel(), Init {

    fun init() {}

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

interface Init {
    fun init(isFirstRun: Boolean)
}