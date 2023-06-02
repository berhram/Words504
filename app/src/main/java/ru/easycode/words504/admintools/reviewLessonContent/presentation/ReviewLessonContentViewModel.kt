package ru.easycode.words504.admintools.reviewLessonContent.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.easycode.words504.admintools.lessonexercise.presentation.AdminLessonExerciseScreen
import ru.easycode.words504.admintools.lessonquote.presentation.AdminLessonQuoteScreen
import ru.easycode.words504.admintools.lessonslist.data.cache.LessonCache
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository
import ru.easycode.words504.admintools.lessontext.presentation.AdminLessonTextScreen
import ru.easycode.words504.admintools.lessonwords.presentation.AdminLessonWordsScreen
import ru.easycode.words504.presentation.BaseViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.recognition.presentation.Init

interface ReviewLessonContentViewModel : Init, Communication.Observe<ReviewLessonContentState> {

    fun choseContent(content: ReviewLessonContentUi)

    class Base(
        private val repository: LessonsListRepository.Read,
        private val mapper: LessonCache.Mapper<List<ReviewLessonContentUi>>,
        private val communication: Communication.Mutable<ReviewLessonContentState>,
        private val navigation: NavigationCommunication.Update,
        dispatchersList: DispatchersList
    ) : BaseViewModel(dispatchersList), ReviewLessonContentViewModel {

        override fun choseContent(content: ReviewLessonContentUi) {
            navigation.map(
                when (content) {
                    is ReviewLessonContentUi.Quote -> AdminLessonQuoteScreen
                    is ReviewLessonContentUi.Words -> AdminLessonWordsScreen
                    is ReviewLessonContentUi.Text -> AdminLessonTextScreen
                    is ReviewLessonContentUi.Exercise -> AdminLessonExerciseScreen
                    else -> {
                        throw IllegalStateException("Not found screen for $content")
                    }
                }
            )
        }

        override fun init(isFirstRun: Boolean) {
            if (isFirstRun) {
                handle({ repository.chosenLesson().map(mapper) }) {
                    communication.map(ReviewLessonContentState.Initial(it))
                }
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<ReviewLessonContentState>) {
            communication.observe(owner, observer)
        }
    }
}
