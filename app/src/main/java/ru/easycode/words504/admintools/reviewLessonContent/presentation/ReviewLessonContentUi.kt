package ru.easycode.words504.admintools.reviewLessonContent.presentation

import ru.easycode.words504.R
import ru.easycode.words504.admintools.data.cloud.ExerciseType
import ru.easycode.words504.presentation.ManageResources

interface ReviewLessonContentUi {

    fun id(): String

    abstract class Abstract(private val manageResources: ManageResources) : ReviewLessonContentUi {
        protected abstract val resId: Int
        override fun id(): String = manageResources.string(resId)
    }

    class Quote(manageResources: ManageResources) : Abstract(manageResources) {
        override val resId = R.string.lesson_quote
    }

    class Words(manageResources: ManageResources) : Abstract(manageResources) {
        override val resId = R.string.lesson_words
    }

    class Text(manageResources: ManageResources) : Abstract(manageResources) {
        override val resId = R.string.lesson_text
    }

    class Exercise(private val manageResources: ManageResources, private val type: ExerciseType) :
        Abstract(manageResources) {
        override val resId = R.string.lesson_exercise
        override fun id(): String = manageResources.string(resId) + " ${type.name}"
    }
}
