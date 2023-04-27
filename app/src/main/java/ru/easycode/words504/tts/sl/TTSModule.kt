package ru.easycode.words504.tts.sl

import android.content.Context
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module
import ru.easycode.words504.tts.presentation.TTSTestViewModelFinal
import ru.easycode.words504.tts.data.TTSEngine
import ru.easycode.words504.tts.presentation.TTSResultCommunicationStates

class TTSModule(private val core: CoreModule, private val context: Context) : Module<TTSTestViewModelFinal> {
    override fun viewModel(): TTSTestViewModelFinal = TTSTestViewModelFinal(
        dispatchers = core.provideDispatchers(),
        ttsEngine = TTSEngine.Base(context),
        resultCommunication = TTSResultCommunicationStates.Base()
    )
}