package ru.easycode.words504.recognition.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.easycode.words504.recognition.STTState

interface ObserveSTT {

    fun observeRecognitionResult(owner: LifecycleOwner, observer: Observer<STTState>)

}