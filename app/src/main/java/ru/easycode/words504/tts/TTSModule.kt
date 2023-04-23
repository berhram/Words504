package ru.easycode.words504.tts

import android.content.Context
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module
import ru.easycode.words504.tts.presentation.TTSCommunication

class TTSModule(private val core: CoreModule, private val context: Context) : Module<TTSTestViewModelFinal> {
    override fun viewModel(): TTSTestViewModelFinal = TTSTestViewModelFinal(
        dispatchers = core.provideDispatchers(),
        ttsEngine = TTSEngine.Base(context),
        communication = TTSCommunication.Base()
    )
}