package ru.easycode.words504.admintools.lessonslist.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import ru.easycode.words504.R

interface LessonUi {

    fun id(): String
    fun map(icon: ImageView, name: TextView, state: TextView)

    abstract class Abstract(
        protected open val id: String
    ) : LessonUi {

        protected abstract val stateIconColorResId: Int
        protected abstract val stateTextResId: Int

        override fun id(): String = id
        override fun map(icon: ImageView, name: TextView, state: TextView) {
            name.text = id
            icon.setColorFilter(ContextCompat.getColor(icon.context, stateIconColorResId))
            state.text = state.context.getText(stateTextResId)
        }
    }

    data class Complete(override val id: String) : Abstract(id) {
        override val stateIconColorResId = R.color.admin_complete
        override val stateTextResId = R.string.complete
    }

    data class InProgress(override val id: String) : Abstract(id) {
        override val stateIconColorResId = R.color.admin_in_progress
        override val stateTextResId = R.string.in_progress
    }
}
