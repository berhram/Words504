package ru.easycode.words504.admintools.initial.sl

import ru.easycode.words504.admintools.initial.presentation.AdminInitialViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

/**
 * @author Asatryan on 02.05.2023
 */
class AdminInitialModule(private val coreModule: CoreModule) : Module<AdminInitialViewModel> {

    override fun viewModel() =
        AdminInitialViewModel(coreModule.provideAdminScope().provideNavigation())
}
