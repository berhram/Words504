package ru.easycode.words504.admintools.sl

import ru.easycode.words504.admintools.presentation.AdminViewModel
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.sl.Module

class AdminModule(
    private val navigationCommunication: NavigationCommunication.Mutable
) : Module<AdminViewModel> {
    override fun viewModel(): AdminViewModel = AdminViewModel(navigationCommunication)
}
