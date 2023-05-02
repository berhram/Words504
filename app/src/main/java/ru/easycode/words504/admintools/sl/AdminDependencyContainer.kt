package ru.easycode.words504.admintools.sl

import androidx.lifecycle.ViewModel
import ru.easycode.words504.admintools.initial.presentation.AdminInitialViewModel
import ru.easycode.words504.admintools.initial.sl.AdminInitialModule
import ru.easycode.words504.admintools.presentation.AdminActivityViewModel
import ru.easycode.words504.admintools.presentation.SentenceViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.DependencyContainer
import ru.easycode.words504.sl.Module

class AdminDependencyContainer(
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        AdminActivityViewModel.Base::class.java -> AdminMainModule(core)
        AdminInitialViewModel::class.java -> AdminInitialModule(core)
        SentenceViewModel.Base::class.java -> AdminSentenceModule(core)
        else -> error.module(clazz)
    }
}
