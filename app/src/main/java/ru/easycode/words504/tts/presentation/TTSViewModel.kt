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
import ru.easycode.words504.presentation.Navigate
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen
import ru.easycode.words504.tts.MediaLevel
import ru.easycode.words504.tts.data.TTSEngine
import ru.easycode.words504.tts.data.TTSObserver
import ru.easycode.words504.tts.domain.TTSError

interface TTSViewModel : Communication.Observe<Screen>, TTSObserver, Navigate {

    fun init(onInitListener: OnInitListener)

    fun speak(phrases: List<String>)

    fun changePlayback()

    fun observeTts(owner: LifecycleOwner, observer: Observer<List<String>>)

    fun observeTTSControl(owner: LifecycleOwner, observer: Observer<Unit>)

    class Base(
        private val dispatchers: DispatchersList,
        private val ttsEngine: TTSEngine,
        private val resultCommunication: TTSStateCommunication,
        private val mediaLevel: MediaLevel,
        private val manageResources: ManageResources,
        private val ttsCommunication: TTSCommunication.Observe,
        private val ttsControlCommunication: TTSControlCommunication.Observe,
        private val navigationCommunication: NavigationCommunication.Mutable
    ) : ViewModel(), TTSViewModel {

        override fun started(phrase: String) {
            viewModelScope.launch(dispatchers.ui()) {
                resultCommunication.map(
                    TTSState.Started("${manageResources.string(R.string.started)}: $phrase")
                )
            }
        }

        override fun finished(phrase: String) {
            viewModelScope.launch(dispatchers.ui()) {
                resultCommunication.map(
                    TTSState.Finished("${manageResources.string(R.string.finished)}: $phrase")
                )
            }
        }

        override fun error(error: TTSError) {}

        override fun navigate(screen: Screen) {
            navigationCommunication.map(screen)
        }

        override fun speak(phrases: List<String>) {
            if (mediaLevel.isLowLevel()) {
                resultCommunication.map(
                    TTSState.Message(manageResources.string(R.string.turn_up_volume))
                )
            } else {
                ttsEngine.speak(phrases)
            }
        }

        override fun changePlayback() {
            ttsEngine.changePlayback()
        }

        override fun observeTts(owner: LifecycleOwner, observer: Observer<List<String>>) {
            ttsCommunication.observe(owner, observer)
        }

        override fun observeTTSControl(owner: LifecycleOwner, observer: Observer<Unit>) {
            ttsControlCommunication.observe(owner, observer)
        }

        override fun init(onInitListener: OnInitListener) {
            ttsEngine.init(onInitListener)
            ttsEngine.addObserver(this)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<Screen>) =
            navigationCommunication.observe(owner, observer)
    }
}
