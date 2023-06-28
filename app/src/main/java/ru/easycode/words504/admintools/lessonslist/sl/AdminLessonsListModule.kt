package ru.easycode.words504.admintools.lessonslist.sl

import ru.easycode.words504.admintools.lessonslist.data.LessonsListRepositoryBase
import ru.easycode.words504.admintools.lessonslist.data.cache.ChosenLessonIdCache
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCacheToUiMapper
import ru.easycode.words504.admintools.lessonslist.presentation.AdminLessonsListViewModel
import ru.easycode.words504.admintools.lessonslist.presentation.LessonsListCommunication
import ru.easycode.words504.data.cache.serialization.Serialization
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class AdminLessonsListModule(private val coreModule: CoreModule) :
    Module<AdminLessonsListViewModel.Base> {

    override fun viewModel(): AdminLessonsListViewModel.Base {
        val adminScope = coreModule.provideAdminScope()
        return AdminLessonsListViewModel.Base(
            repository = LessonsListRepositoryBase(
                adminScope.provideDatabase().lessonsDao(),
                ChosenLessonIdCache.Base(adminScope.provideObjectStorage()),
                Serialization.Base()
            ),
            communication = LessonsListCommunication.Base(),
            mapper = LessonCacheToUiMapper(),
            navigation = coreModule.provideAdminScope().provideNavigation(),
            resources = coreModule,
            dispatchersList = coreModule.provideDispatchers()
        )
    }
}
