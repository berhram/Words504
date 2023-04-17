package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainViewModel
import ru.easycode.words504.rsrc.TestTranslateViewModel

class BaseDependencyContainer(
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        TestTranslateViewModel::class.java -> CoroutinesTestModule(core)
        MainViewModel::class.java -> MainModule()
        else -> error.module(clazz)
    }
}
