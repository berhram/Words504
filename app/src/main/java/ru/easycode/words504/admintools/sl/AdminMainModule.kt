package ru.easycode.words504.admintools.sl

import ru.easycode.words504.admintools.presentation.AdminViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class AdminMainModule(private val core: CoreModule) : Module<AdminViewModel> {
    override fun viewModel(): AdminViewModel =
        AdminViewModel(core.provideAdminScope().provideNavigation())
}
