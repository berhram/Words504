package ru.easycode.words504.admintools.sl

import ru.easycode.words504.admintools.presentation.AdminSentenceCommunication
import ru.easycode.words504.admintools.presentation.SentenceViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class AdminSentenceModule(private val core: CoreModule) : Module<SentenceViewModel.Base> {

    override fun viewModel() =
        SentenceViewModel.Base(
            core.provideAdminScope().provideSentenceUiCache(),
            core.provideAdminScope().provideNavigation(),
            AdminSentenceCommunication.Base()
        )
}
