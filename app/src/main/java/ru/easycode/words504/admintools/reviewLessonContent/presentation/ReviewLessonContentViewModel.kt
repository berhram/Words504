package ru.easycode.words504.admintools.reviewLessonContent.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCache
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.presentation.BaseViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.Init
import ru.easycode.words504.presentation.NavigationCommunication

interface ReviewLessonContentViewModel : Init, Communication.Observe<ReviewLessonContentState> {

    fun choseContent(content: ReviewLessonContentUi)

    class Base(
        private val repository: LessonsListRepository.Read,
        private val mapper: LessonCache.Mapper<List<ReviewLessonContentUi>>,
        private val communication: Communication.Mutable<ReviewLessonContentState>,
        private val navigation: NavigationCommunication.Update,
        dispatchersList: DispatchersList
    ) : BaseViewModel(dispatchersList), ReviewLessonContentViewModel {

        override fun choseContent(content: ReviewLessonContentUi) = content.map(navigation)

        override fun init() {
            handle({ repository.chosenLesson().map(mapper) }) {
                communication.map(ReviewLessonContentState.Initial(it))
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<ReviewLessonContentState>) {
            communication.observe(owner, observer)
        }
    }
}
