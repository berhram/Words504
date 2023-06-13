package ru.easycode.words504.admintools.reviewLessonContent.presentation

import ru.easycode.words504.R
import ru.easycode.words504.admintools.data.cloud.LessonExerciseCloud
import ru.easycode.words504.admintools.data.cloud.LessonQuoteCloud
import ru.easycode.words504.admintools.data.cloud.LessonTextCloud
import ru.easycode.words504.admintools.data.cloud.LessonWordCloud
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
        private val data: LessonQuoteCloud,
        private val screen: Screen
    ) : Abstract(manageResources, screen) {
        override val resId = R.string.lesson_quote
    }

    data class Words(
        private val manageResources: ManageResources,
        private val data: List<LessonWordCloud>,
        private val screen: Screen
    ) : Abstract(manageResources, screen) {
        override val resId = R.string.lesson_words
    }

    data class Text(
        private val manageResources: ManageResources,
        private val data: LessonTextCloud,
        private val screen: Screen
    ) : Abstract(manageResources, screen) {
        override val resId = R.string.lesson_text
    }

    data class Exercise(
        private val manageResources: ManageResources,
        private val data: LessonExerciseCloud,
        private val screen: Screen
    ) : Abstract(manageResources, screen) {
        override val resId = R.string.lesson_exercise
        override fun id(): String = manageResources.string(resId)
    }
}
