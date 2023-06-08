package ru.easycode.words504.admintools.reviewLessonContent.presentation

import ru.easycode.words504.R
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.data.cloud.LessonQuoteCloud
import ru.easycode.words504.admintools.data.cloud.LessonTextCloud
import ru.easycode.words504.admintools.data.cloud.LessonWordCloud
import ru.easycode.words504.admintools.lessonexercise.presentation.AdminLessonExerciseScreen
import ru.easycode.words504.admintools.lessonquote.presentation.AdminLessonQuoteScreen
import ru.easycode.words504.admintools.lessontext.presentation.AdminLessonTextScreen
import ru.easycode.words504.admintools.lessonwords.presentation.AdminLessonWordsScreen
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.presentation.NavigationCommunication

interface ReviewLessonContentUi {

    fun id(): String
    fun map(navigation: NavigationCommunication.Update)

    abstract class Abstract(private val manageResources: ManageResources) : ReviewLessonContentUi {
        protected abstract val resId: Int
        override fun id(): String = manageResources.string(resId)
    }

    data class Quote(
        private val manageResources: ManageResources,
        private val data: LessonQuoteCloud
    ) : Abstract(manageResources) {
        override val resId = R.string.lesson_quote
        override fun map(navigation: NavigationCommunication.Update) =
            navigation.map(AdminLessonQuoteScreen)
    }

    data class Words(
        private val manageResources: ManageResources,
        private val data: List<LessonWordCloud>
    ) : Abstract(manageResources) {
        override val resId = R.string.lesson_words
        override fun map(navigation: NavigationCommunication.Update) =
            navigation.map(AdminLessonWordsScreen)
    }

    data class Text(
        private val manageResources: ManageResources,
        private val data: LessonTextCloud
    ) : Abstract(manageResources) {
        override val resId = R.string.lesson_text
        override fun map(navigation: NavigationCommunication.Update) =
            navigation.map(AdminLessonTextScreen)
    }

    data class Exercise(
        private val manageResources: ManageResources,
        private val data: LessonExerciseCloud
    ) : Abstract(manageResources) {
        override val resId = R.string.lesson_exercise
        override fun id(): String = manageResources.string(resId)

        override fun map(navigation: NavigationCommunication.Update) =
            navigation.map(AdminLessonExerciseScreen)
    }
}
