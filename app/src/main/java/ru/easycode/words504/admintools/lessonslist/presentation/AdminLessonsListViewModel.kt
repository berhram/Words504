package ru.easycode.words504.admintools.lessonslist.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.easycode.words504.admintools.chooseLessonType.presentation.ChooseLessonTypeScreen
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCache
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.admintools.reviewLessonContent.presentation.ReviewLessonContentScreen
import ru.easycode.words504.presentation.BaseViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.Init
import ru.easycode.words504.presentation.NavigationCommunication

interface AdminLessonsListViewModel : Init {

    fun share(id: String)
    fun chooseLesson(id: String)
    fun addLesson()

    class Base(
        private val repository: LessonsListRepository.Mutable,
        private val communication: Communication.Mutable<LessonsUi>,
        private val mapper: LessonCache.Mapper<LessonUi>,
        private val navigation: NavigationCommunication.Update,
        dispatchersList: DispatchersList
    ) : BaseViewModel(dispatchersList),
        AdminLessonsListViewModel,
        Communication.Observe<LessonsUi> {

        override fun init() {
            handle({ repository.lessons() }) { result ->
                communication.map(LessonsUi.Initial(result.map { it.map(mapper) }))
            }
        }

        override fun chooseLesson(id: String) {
            repository.saveChooseLesson(id)
            navigation.map(ReviewLessonContentScreen(id))
        }

        override fun addLesson() = navigation.map(ChooseLessonTypeScreen)

        override fun share(id: String) {
            handle({ repository.lessonToString(id) }) {
                communication.map(LessonsUi.Share(it))
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<LessonsUi>) =
            communication.observe(owner, observer)
    }
}
