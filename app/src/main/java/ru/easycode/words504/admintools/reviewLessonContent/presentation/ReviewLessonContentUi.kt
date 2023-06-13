package ru.easycode.words504.admintools.reviewLessonContent.presentation

import ru.easycode.words504.R
import ru.easycode.words504.admintools.lessonexercise.presentation.AdminLessonExerciseScreen
import ru.easycode.words504.admintools.lessonquote.presentation.AdminLessonQuoteScreen
import ru.easycode.words504.admintools.lessontext.presentation.AdminLessonTextScreen
import ru.easycode.words504.admintools.lessonwords.presentation.AdminLessonWordsScreen
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.ExercisePreview
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.QuotePreview
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.TextPreview
import ru.easycode.words504.admintools.reviewLessonContent.presentation.models.WordsPreview
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

interface ReviewLessonContentUi {

    fun id(): String
    fun map(navigation: NavigationCommunication.Update)

    abstract class Abstract(
        private val manageResources: ManageResources,
        private val screen: Screen
    ) : ReviewLessonContentUi {
        protected abstract val resId: Int

        override fun id(): String = manageResources.string(resId)

        override fun map(navigation: NavigationCommunication.Update) {
            navigation.map(screen)
        }
    }

    data class Quote(
        private val manageResources: ManageResources,
        private val preview: QuotePreview
    ) : Abstract(manageResources, AdminLessonQuoteScreen) {
        override val resId = R.string.lesson_quote
    }

    data class Words(
        private val manageResources: ManageResources,
        private val preview: WordsPreview
    ) : Abstract(manageResources, AdminLessonWordsScreen) {
        override val resId = R.string.lesson_words
    }

    data class Text(
        private val manageResources: ManageResources,
        private val preview: TextPreview
    ) : Abstract(manageResources, AdminLessonTextScreen) {
        override val resId = R.string.lesson_text
    }

    data class Exercise(
        private val manageResources: ManageResources,
        private val preview: ExercisePreview
    ) : Abstract(manageResources, AdminLessonExerciseScreen) {
        override val resId = R.string.lesson_exercise
        override fun id(): String = manageResources.string(resId)
    }
}
