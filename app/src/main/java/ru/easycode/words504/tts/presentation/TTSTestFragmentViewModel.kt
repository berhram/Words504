package ru.easycode.words504.tts.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.easycode.words504.tts.data.TTSObserver
import ru.easycode.words504.tts.data.TTSObserversStorage

interface TTSTestFragmentViewModel : TTSObserver {

    fun speak(text: String)

    class Base(
        private val ttsCommunication: TTSCommunication.Update,
        private val ttsObserversStorage: TTSObserversStorage
    ) : ViewModel(), TTSTestFragmentViewModel {

        init {
            ttsObserversStorage.add(this)
        }

        override fun speak(text: String) {
            ttsCommunication.map(text.split("."))
        }

        override fun started(phrase: String) {
            Log.d("TEST", "started $phrase")
        }

        override fun finished(phrase: String) {
            Log.d("TEST", "finished $phrase")
        }

        override fun onCleared() {
            super.onCleared()
            ttsObserversStorage.remove(this)
        }
    }
}
