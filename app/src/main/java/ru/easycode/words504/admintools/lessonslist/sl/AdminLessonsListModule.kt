package ru.easycode.words504.admintools.lessonslist.sl

import ru.easycode.words504.admintools.lessonslist.data.LessonsListRepositoryBase
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonUiMapper
import ru.easycode.words504.admintools.lessonslist.presentation.AdminLessonsListViewModel
import ru.easycode.words504.admintools.lessonslist.presentation.LessonsListCommunication
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class AdminLessonsListModule(private val coreModule: CoreModule) :
    Module<AdminLessonsListViewModel.Base> {

    override fun viewModel(): AdminLessonsListViewModel.Base {
        val adminScope = coreModule.provideAdminScope()
        return AdminLessonsListViewModel.Base(
            repository = LessonsListRepositoryBase(
                adminScope.provideAdminDatabase().lessonsDao(),
                adminScope.provideSimpleStorage()
            ),
            communication = LessonsListCommunication.Base(),
            mapper = LessonUiMapper(),
            navigation = coreModule.provideAdminScope().provideNavigation(),
            dispatchersList = coreModule.provideDispatchers()
        )
    }
}
