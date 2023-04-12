package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainViewModel

class BaseDependencyContainer(
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        MainViewModel::class.java -> MainModule()
        else -> error.module(clazz)
    }
}
