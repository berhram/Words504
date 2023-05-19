package ru.easycode.words504.tts.presentation

import android.speech.tts.TextToSpeech.OnInitListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.easycode.words504.R
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.tts.MediaLevel
import ru.easycode.words504.tts.data.TTSCallback
import ru.easycode.words504.tts.data.TTSEngine

interface TTSViewModel : Communication.Observe<TTSState>, TTSCallback {

    fun init(onInitListener: OnInitListener)

    fun speak(phrases: List<String>)

    class Base(
        private val dispatchers: DispatchersList,
        private val ttsEngine: TTSEngine,
        private val resultCommunication: TTSStateCommunication,
        private val mediaLevel: MediaLevel,
        private val manageResources: ManageResources
    ) : ViewModel(), TTSViewModel {

        override fun finished(phrase: String) {
            viewModelScope.launch(dispatchers.ui()) {
                if (mediaLevel.isLowLevel()) {
                    resultCommunication.map(
                        TTSState.Finished(manageResources.string(R.string.turn_up_volume))
                    )
                } else {
                    resultCommunication.map(TTSState.Finished(phrase))
                }
            }
        }

        override fun speak(phrases: List<String>) {
            ttsEngine.speak(phrases)
        }

        override fun init(onInitListener: OnInitListener) = ttsEngine.init(onInitListener, this)

        override fun observe(owner: LifecycleOwner, observer: Observer<TTSState>) =
            resultCommunication.observe(owner, observer)
    }
}
