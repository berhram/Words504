package ru.easycode.words504.admintools.sl

import ru.easycode.words504.admintools.presentation.AdminSentenceViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class AdminSentenceModule(private val core: CoreModule) : Module<AdminSentenceViewModel.Base> {

    override fun viewModel() =
        AdminSentenceViewModel.Base(core.provideAdminScope().provideNavigation())
}
