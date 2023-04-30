package ru.easycode.words504.admintools.sl

import androidx.lifecycle.ViewModel
import ru.easycode.words504.admintools.presentation.AdminSentenceViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.DependencyContainer
import ru.easycode.words504.sl.Module

class AdminDependencyContainer(
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        AdminSentenceViewModel::class.java -> AdminMainModule(core)
        else -> error.module(clazz)
    }
}
