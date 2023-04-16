package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import ru.easycode.words504.MainViewModel
import ru.easycode.words504.admintools.presentation.AdminViewModel
import ru.easycode.words504.admintools.sl.AdminModule

class BaseDependencyContainer(
    private val core: CoreModule,
    private val error: DependencyContainer = DependencyContainer.Error()
) : DependencyContainer {
    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> = when (clazz) {
        MainViewModel::class.java -> MainModule()
        AdminViewModel::class.java -> AdminModule(core.provideAdminScope().provideNavigation())
        else -> error.module(clazz)
    }
}
