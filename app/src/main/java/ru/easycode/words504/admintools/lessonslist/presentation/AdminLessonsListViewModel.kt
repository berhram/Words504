package ru.easycode.words504.admintools.lessonslist.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.easycode.words504.admintools.chooseLessonType.presentation.ChooseLessonTypeScreen
import ru.easycode.words504.admintools.lessonslist.data.LessonCache
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.admintools.reviewLessonContent.presentation.ReviewLessonContentScreen
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.Init
import ru.easycode.words504.presentation.NavigationCommunication

interface AdminLessonsListViewModel : Init {

    fun share(id: String)
    fun chooseLesson(id: String)
    fun addLesson()

    class Base(
        private val repository: LessonsListRepository,
        private val communication: Communication.Mutable<LessonsUi>,
        private val mapper: LessonCache.Mapper<LessonUi>,
        private val navigation: NavigationCommunication.Update
    ) : ViewModel(), AdminLessonsListViewModel, Communication.Observe<LessonsUi> {

        override fun init() {
            val result = repository.lessons()
            communication.map(LessonsUi.Initial(result.map { it.map(mapper) }))
        }

        override fun chooseLesson(id: String) {
            repository.chooseLesson(id)
            navigation.map(ReviewLessonContentScreen)
        }

        override fun addLesson() = navigation.map(ChooseLessonTypeScreen)

        override fun share(id: String) =
            communication.map(LessonsUi.Share(repository.lessonToString(id)))

        override fun observe(owner: LifecycleOwner, observer: Observer<LessonsUi>) =
            communication.observe(owner, observer)
    }
}