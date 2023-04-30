package ru.easycode.words504.admintools.sl

import ru.easycode.words504.admintools.presentation.AdminSentenceViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class AdminMainModule(private val core: CoreModule) : Module<AdminSentenceViewModel> {
    override fun viewModel(): AdminSentenceViewModel =
        AdminSentenceViewModel(core.provideAdminScope().provideNavigation())
}
