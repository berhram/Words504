package ru.easycode.words504.admintools.lessonslist.sl

import android.util.Log
import ru.easycode.words504.admintools.lessonslist.data.LessonCache
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.admintools.lessonslist.presentation.AdminLessonsListViewModel
import ru.easycode.words504.admintools.lessonslist.presentation.LessonUi
import ru.easycode.words504.admintools.lessonslist.presentation.LessonsListCommunication
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

        private class LessonCacheSimple(
            private val id: String,
            private val isComplete: Boolean
        ) : LessonCache {
            override fun <T> map(mapper: LessonCache.Mapper<T>): T = mapper.map(id, isComplete)
        }

        override fun lessons(): List<LessonCache> = listOf(
            LessonCacheSimple("Lesson 1", true),
            LessonCacheSimple("Lesson 2", false),
            LessonCacheSimple("Lesson 3", true),
            LessonCacheSimple("Lesson 4", false),
            LessonCacheSimple("Lesson 5", true)
        )

        override fun chooseLesson(id: String) {
            Log.d("Lessons", "chooseLesson $id")
        }

        override fun lessonToString(id: String): String = "Example string"
    }

    // todo remove it after true implementation
    private class Mapper : LessonCache.Mapper<LessonUi> {
        override fun map(id: String, isComplete: Boolean) =
            if (isComplete) LessonUi.Complete(id) else LessonUi.InProgress(id)
    }
}
