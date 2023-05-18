package ru.easycode.words504.admintools.lessonslist.sl

import ru.easycode.words504.admintools.lessonslist.data.LessonCache
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.admintools.lessonslist.presentation.AdminLessonsListViewModel
import ru.easycode.words504.admintools.lessonslist.presentation.LessonState
import ru.easycode.words504.admintools.lessonslist.presentation.LessonUi
import ru.easycode.words504.admintools.lessonslist.presentation.LessonsListCommunication
import ru.easycode.words504.presentation.Screen
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class AdminLessonsListModule(private val coreModule: CoreModule) :
    Module<AdminLessonsListViewModel.Base> {

    override fun viewModel(): AdminLessonsListViewModel.Base {
        return AdminLessonsListViewModel.Base(
            repository = LessonsListRepositoryImp(),
            communication = LessonsListCommunication.Base(),
            mapper = Mapper(),
            navigation = coreModule.provideAdminScope().provideNavigation()
        )
    }

    // todo remove it after true implementation
    private class LessonsListRepositoryImp : LessonsListRepository {
        override fun lessons(): List<LessonCache> = emptyList()
        override fun lesson(): LessonCache {
            TODO("Not yet implemented")
        }

        override fun lessonToString(id: String): String {
            TODO("Not yet implemented")
        }
    }

    // todo remove it after true implementation
    private class Mapper : LessonCache.Mapper<LessonUi> {
        override fun map(id: String, isComplete: Boolean, lastEditScreen: Screen) =
            LessonUi(id, if (isComplete) LessonState.Complete else LessonState.InProgress)
    }
}
