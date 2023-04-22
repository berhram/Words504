package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainViewModel
import ru.easycode.words504.languages.presentation.ChooseLanguageViewModel
import ru.easycode.words504.languages.sl.ChooseLanguageModule

class BaseDependencyContainer(
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        MainViewModel::class.java -> MainModule()
        ChooseLanguageViewModel.Base::class.java -> ChooseLanguageModule(core)
        else -> error.module(clazz)
    }
}
