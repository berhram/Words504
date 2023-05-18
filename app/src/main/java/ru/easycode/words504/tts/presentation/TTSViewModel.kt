package ru.easycode.words504.tts.presentation

import android.speech.tts.TextToSpeech.OnInitListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.tts.TTSState
import ru.easycode.words504.tts.data.TTSCallback
import ru.easycode.words504.tts.data.TTSEngine


interface TTSViewModel : TTSResultCommunication, TTSCallback {

    fun init(onInitListener: OnInitListener)

    fun ttsString(sentence: String)

    class Base(
        private val dispatchers: DispatchersList,
        private val ttsEngine: TTSEngine.Base,
        private val resultCommunication: TTSResultCommunicationStates.Mutable
    ) : ViewModel(), TTSViewModel {

        override fun finished() {
            viewModelScope.launch(dispatchers.ui()) {
                resultCommunication.map(TTSState.Finished())
            }
        }

        override fun init(onInitListener: OnInitListener) = ttsEngine.init(onInitListener, this)

        override fun ttsString(sentence: String) = ttsEngine.speak(sentence)

        override fun observeTTSResult(owner: LifecycleOwner, observer: Observer<TTSState>) =
            resultCommunication.observe(owner, observer)
    }
}
