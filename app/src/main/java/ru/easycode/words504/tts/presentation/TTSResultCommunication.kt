package ru.easycode.words504.tts.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface TTSResultCommunication {
    fun observeTTSResult(owner: LifecycleOwner, observer: Observer<TTSState>)
}
