package ru.easycode.words504.tts.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.easycode.words504.R
import ru.easycode.words504.presentation.BaseViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.tts.MediaLevel
import ru.easycode.words504.tts.data.ObserversStorage
import ru.easycode.words504.tts.data.TTSControl
import ru.easycode.words504.tts.data.TTSControlObserver
import ru.easycode.words504.tts.data.TTSObserver
import ru.easycode.words504.tts.domain.TTSError

interface TTSTestFragmentViewModel : TTSObserver, TTSControl, TTSControlObserver {

    fun speak(text: String)

    class Base(
        private val dispatchers: DispatchersList,
        private val ttsCommunication: TTSCommunication.Update,
        private val ttsObserversStorage: ObserversStorage<TTSObserver>,
        private val ttsObserverControlStorage: ObserversStorage<TTSControlObserver>,
        private val ttsControlCommunication: TTSControlCommunication.Update,
        private val uiStateCommunication: Communication.Mutable<TTSTestFragmentUIState>,
        private val manageResources: ManageResources,
        private val mediaLevel: MediaLevel
    ) : BaseViewModel(dispatchers), TTSTestFragmentViewModel,
        Communication.Observe<TTSTestFragmentUIState> {

        init {
            ttsObserversStorage.add(this)
            ttsObserverControlStorage.add(this)
        }

        override fun speak(text: String) {
            if (mediaLevel.isLowLevel()) {
                uiStateCommunication.map(
                    TTSTestFragmentUIState.Error(
                        manageResources.string(R.string.turn_up_volume),
                        manageResources
                    )
                )
            } else {
                ttsCommunication.map(text.split(". ", ": ", "? "))
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<TTSTestFragmentUIState>) {
            uiStateCommunication.observe(owner, observer)
        }

        override fun started(phrase: String) {
            viewModelScope.launch(dispatchers.ui()) {
                uiStateCommunication.map(TTSTestFragmentUIState.Start(phrase, manageResources))
            }
        }

        override fun finished(phrase: String) {
            viewModelScope.launch(dispatchers.ui()) {
                uiStateCommunication.map(TTSTestFragmentUIState.Finish(phrase, manageResources))
            }
        }

        override fun error(error: TTSError) {
            viewModelScope.launch(dispatchers.ui()) {
                uiStateCommunication.map(
                    TTSTestFragmentUIState.Error(
                        error.string(
                            manageResources
                        ),
                        manageResources
                    )
                )
            }
        }

        override fun changePlayback() = ttsControlCommunication.map(Unit)

        override fun paused() {
            viewModelScope.launch(dispatchers.ui()) {
                uiStateCommunication.map(
                    TTSTestFragmentUIState.Pause("", manageResources)
                )
            }
        }

        override fun resumed() {
            viewModelScope.launch(dispatchers.ui()) {
                uiStateCommunication.map(TTSTestFragmentUIState.Resume("", manageResources))
            }
        }

        override fun onCleared() {
            super.onCleared()
            ttsObserversStorage.remove(this)
            ttsObserverControlStorage.remove(this)
        }
    }
}
