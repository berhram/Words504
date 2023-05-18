package ru.easycode.words504.admintools.lessonslist.sl

import ru.easycode.words504.admintools.lessonslist.presentation.AdminLessonsListViewModel
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class AdminLessonsListModule(private val coreModule: CoreModule) : Module<AdminLessonsListViewModel> {

    override fun viewModel() =
        AdminLessonsListViewModel(coreModule.provideAdminScope().provideNavigation())
}
