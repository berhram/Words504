package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainViewModel
import ru.easycode.words504.loading.LoadCoroutinesModule
import ru.easycode.words504.loading.LoadTranslateViewModel

class BaseDependencyContainer(
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        LoadTranslateViewModel::class.java -> LoadCoroutinesModule(core)
        MainViewModel::class.java -> MainModule()
        else -> error.module(clazz)
    }
}
