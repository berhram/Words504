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

abstract class TTSViewModel(
    private val dispatchers: DispatchersList,
    private val ttsEngine: TTSEngine.Base,
    private val resultCommunication: TTSResultCommunicationStates.Mutable
) : ViewModel(), Init, TTSResultCommunication, TTSActions {

    private val callback = object : TTSCallback {
        override fun started(utteranceId: String) {
            viewModelScope.launch(dispatchers.ui()) {
                resultCommunication.map(
                    TTSState.Started(utteranceId)
                )
            }
        }

        override fun finished(utteranceId: String) {
            viewModelScope.launch(dispatchers.ui()) {
                resultCommunication.map(TTSState.Finished(utteranceId))
            }
        }

        override fun partialFinished(utteranceId: String) {
            viewModelScope.launch(dispatchers.ui()) {
                resultCommunication.map(TTSState.PartialFinished(utteranceId))
            }
        }

        override fun error(utteranceId: String, errorCode: Int) {
            viewModelScope.launch(dispatchers.ui()) {
                resultCommunication.map(TTSState.Error(utteranceId))
            }
        }

        override fun stopped(utteranceId: String, interrupted: Boolean) {
            viewModelScope.launch(dispatchers.ui()) {
                resultCommunication.map(
                    TTSState.Stopped("$utteranceId interrupted: $interrupted")
                )
            }
        }
    }

    override fun init(onInitListener: OnInitListener) = ttsEngine.init(onInitListener, callback)

    override fun ttsString(sentence: String) = ttsEngine.speak(sentence)

    override fun ttsStringList(sentences: List<String>) = ttsEngine.speak(sentences)

    override fun stop() = ttsEngine.stop()

    override fun observeTTSResult(owner: LifecycleOwner, observer: Observer<TTSState>) =
        resultCommunication.observe(owner, observer)

}

class TTSTestViewModelFinal(
    dispatchers: DispatchersList,
    ttsEngine: TTSEngine.Base,
    resultCommunication: TTSResultCommunicationStates.Mutable
) : TTSViewModel(dispatchers, ttsEngine, resultCommunication)

interface Init {
    fun init(onInitListener: OnInitListener)
}

interface TTSActions {
    fun ttsString(sentence: String)
    fun ttsStringList(sentences: List<String>)
    fun stop()
}
