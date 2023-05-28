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
        private val repository: LessonsListRepository,
        private val communication: Communication.Mutable<LessonsUi>,
        private val mapper: LessonCache.Mapper<LessonUi>,
        private val navigation: NavigationCommunication.Update,
        dispatchersList: DispatchersList
    ) : BaseViewModel(dispatchersList),
        AdminLessonsListViewModel,
        Communication.Observe<LessonsUi> {

        override fun init() {
            handle({
                saveData() // todo remove it later, now it need for demonstrate
                repository.lessons()
            }) { result ->
                communication.map(LessonsUi.Initial(result.map { it.map(mapper) }))
            }
        }

        // todo remove it later
        private suspend fun saveData() {
            repository.save(LessonCache.Base("lesson1", true, "json string"))
            repository.save(LessonCache.Base("lesson2", false, "json string"))
            repository.save(LessonCache.Base("lesson3", false, "json string"))
        }

        override fun chooseLesson(id: String) {
            repository.chooseLesson(id)
            navigation.map(ReviewLessonContentScreen)
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
