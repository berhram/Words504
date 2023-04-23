package ru.easycode.words504.tts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.withContext
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.tts.presentation.TTSCommunication

abstract class TTSViewModel(
    private val dispatchers: DispatchersList,
    private val ttsEngine: TTSEngine.Base,
    private val communication: Communication.Mutable<List<String>>
) : ViewModel(),
    Init {

    abstract fun ttsString(string: String)

    abstract fun ttsStringList(stringList: List<String>)

    abstract fun pause()

    abstract fun stop()
}

class TTSTestViewModelFinal(
    private val dispatchers: DispatchersList,
    private val ttsEngine: TTSEngine.Base,
    private val communication: Communication.Mutable<List<String>>
) : TTSViewModel(dispatchers, ttsEngine, communication) {

    override fun ttsString(string: String) {

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

    override fun init(isFirstRun: Boolean) {

        ttsEngine.init {
        }
    }

}

interface Init {
    fun init(isFirstRun: Boolean)
}