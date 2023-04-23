package ru.easycode.words504.tts

import androidx.lifecycle.ViewModel
import ru.easycode.words504.presentation.DispatchersList

abstract class TTSViewModel(private val dispatchers: DispatchersList, ) : ViewModel() {

    abstract fun ttsString(string: String)

    abstract fun ttsStringList(stringList: List<String>)

    abstract fun pause()

    abstract fun stop()
}

class TTSTestViewModelFinal(private val dispatchers: DispatchersList) : TTSViewModel(dispatchers) {
    val ttsEngine = TTSEngine.Base()

    override fun ttsString(string: String) {
        ttsEngine.speak(binding.textInputEditText.text.toString())
    }

    override fun ttsStringList(stringList: List<String>) {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

}