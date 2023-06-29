package ru.easycode.words504.tts.presentation

import android.speech.tts.TextToSpeech.OnInitListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.easycode.words504.presentation.BaseViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.Navigate
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen
import ru.easycode.words504.tts.data.TTSEngine

interface TTSViewModel : Communication.Observe<Screen>, Navigate {

    fun init(onInitListener: OnInitListener)

    fun speak(phrases: List<String>)

    fun changePlayback()

    fun observeTts(owner: LifecycleOwner, observer: Observer<List<String>>)

    fun observeTTSControl(owner: LifecycleOwner, observer: Observer<Unit>)

    class Base(
        dispatchers: DispatchersList,
        private val ttsEngine: TTSEngine,
        private val ttsCommunication: TTSCommunication.Observe,
        private val ttsControlCommunication: TTSControlCommunication.Observe,
        private val navigationCommunication: NavigationCommunication.Mutable
    ) : BaseViewModel(dispatchers), TTSViewModel {

        override fun navigate(screen: Screen) {
            navigationCommunication.map(screen)
        }

        override fun speak(phrases: List<String>) {
            ttsEngine.speak(phrases)
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
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<Screen>) =
            navigationCommunication.observe(owner, observer)
    }
}
