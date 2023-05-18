package ru.easycode.words504.admintools.lessonslist.presentation

import ru.easycode.words504.admintools.lessonslist.data.LessonCache
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.admintools.presentation.AdminNavigate
import ru.easycode.words504.presentation.BaseViewModel
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

interface AdminLessonsListViewModel {
    fun init()
    fun chooseLesson(id: Int)

    class Base(
        private val repository: LessonsListRepository,
        private val communication: LessonsListCommunication,
        private val mapper: LessonCache.Mapper<LessonUi>,
        private val navigation: NavigationCommunication.Update,
        dispatchersList: DispatchersList
    ) : BaseViewModel(dispatchersList), AdminNavigate, AdminLessonsListViewModel {

        override fun init() {
            communication.map(LessonsUi.Loading)
            handle({ repository.lessons() }) { lessonsList ->
                communication.map(LessonsUi.Success(lessonsList.map { it.map(mapper) }))
            }
        }

        override fun chooseLesson(id: Int) {
            // TODO ?navigate to last complete exercise screen if state InWork else to first screen(QuoteScreen)
        }

        override fun navigate(screen: Screen) = navigation.map(screen)
    }
}
