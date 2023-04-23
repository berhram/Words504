package ru.easycode.words504.tts

import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class TTSModule(private val core: CoreModule) : Module<TTSTestViewModelFinal> {
    override fun viewModel(): TTSTestViewModelFinal = TTSTestViewModelFinal(
        dispatchers = core.provideDispatchers(),
    )
}