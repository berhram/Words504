package ru.easycode.words504.recognition.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.easycode.words504.recognition.STTState

interface STTCommunication : ObserveSTT {
    fun putRecognitionResult(state: STTState)

    class Base(private val recognitionResult: RecognitionResultCommunication.Mutable) :
        STTCommunication {
        override fun putRecognitionResult(state: STTState) {
            recognitionResult.map(state)
        }

        override fun observeRecognitionResult(
            owner: LifecycleOwner,
            observer: Observer<STTState>
        ) {
            recognitionResult.observe(owner, observer)
        }
    }
}

interface ObserveSTT {

    fun observeRecognitionResult(owner: LifecycleOwner, observer: Observer<STTState>)

}