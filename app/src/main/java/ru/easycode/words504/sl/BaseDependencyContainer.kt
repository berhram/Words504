package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainViewModel
import ru.easycode.words504.loading.LoadCoroutinesModule
import ru.easycode.words504.loading.LoadTranslateViewModel
import ru.easycode.words504.tts.TTSModule
import ru.easycode.words504.tts.TTSTestViewModelFinal

class BaseDependencyContainer(
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        LoadTranslateViewModel::class.java -> LoadCoroutinesModule(core)
        MainViewModel::class.java -> MainModule()
        TTSTestViewModelFinal::class.java -> TTSModule(core)
        else -> error.module(clazz)
    }
}
