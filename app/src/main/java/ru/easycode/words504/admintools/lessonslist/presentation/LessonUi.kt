package ru.easycode.words504.admintools.lessonslist.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import ru.easycode.words504.R

interface LessonUi {

    fun id(): String
    fun map(icon: ImageView, name: TextView, state: TextView)

    data class Base(
        private val id: String,
        private val status: LessonState
    ) : LessonUi {

        override fun id(): String = id

        override fun map(icon: ImageView, name: TextView, state: TextView) {
            name.text = id
            icon.setColorFilter(
                ContextCompat.getColor(
                    icon.context,
                    if (status is LessonState.InProgress) {
                        R.color.admin_in_progress
                    } else {
                        R.color.admin_complete
                    }
                )
            )

            state.text = state.context.getText(
                if (status is LessonState.InProgress) {
                    R.string.in_progress
                } else {
                    R.string.complete
                }
            )
        }
    }
}
