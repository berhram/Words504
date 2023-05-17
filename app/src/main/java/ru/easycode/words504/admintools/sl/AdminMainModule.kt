package ru.easycode.words504.admintools.sl

import ru.easycode.words504.admintools.presentation.AdminActivityViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class AdminMainModule(private val core: CoreModule) : Module<AdminActivityViewModel.Base> {
    override fun viewModel(): AdminActivityViewModel.Base =
        AdminActivityViewModel.Base(core.provideAdminScope().provideNavigation())
}
