package ru.easycode.words504.admintools.lessonslist.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.easycode.words504.admintools.lessonslist.data.LessonCache
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.admintools.presentation.AdminNavigate
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

interface AdminLessonsListViewModel : ChooseLesson, ShareLesson, AdminNavigate {
    fun init()
    fun addLesson()
    fun observeShareString(owner: LifecycleOwner, observer: Observer<String>)

    class Base(
        private val repository: LessonsListRepository,
        private val communication: Communication.Mutable<LessonsUi>,
        private val shareCommunication: Communication.Mutable<String>,
        private val mapper: LessonCache.Mapper<LessonUi>,
        private val navigation: NavigationCommunication.Update
    ) : ViewModel(), AdminLessonsListViewModel, Communication.Observe<LessonsUi> {

        override fun init() {
            val result = repository.lessons()
            communication.map(LessonsUi.Success(result.map { it.map(mapper) }))
        }

        override fun chooseLesson(id: String) {
            val lesson = repository.lesson()
            navigation.map(
                if (lesson.map(LessonCache.Complete())) {
                    Screen.Pop // todo change to LessonTypeScreen when will be created
                } else {
                    lesson.map(LessonCache.LastEditScreen())
                }
            )
        }

        // TODO change Screen.Pop to LessonTypeScreen when it will be created
        override fun addLesson() = navigation.map(Screen.Pop)

        override fun share(id: String) = shareCommunication.map(repository.lessonToString(id))

        override fun navigate(screen: Screen) = navigation.map(screen)

        override fun observe(owner: LifecycleOwner, observer: Observer<LessonsUi>) {
            communication.observe(owner, observer)
        }

        override fun observeShareString(owner: LifecycleOwner, observer: Observer<String>) {
            shareCommunication.observe(owner, observer)
        }
    }
}
