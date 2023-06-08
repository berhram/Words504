package ru.easycode.words504.admintools.reviewLessonContent.presentation

import androidx.appcompat.app.ActionBar
import ru.easycode.words504.presentation.Screen

/**
 * @author Asatryan on 22.05.2023
 */
class ReviewLessonContentScreen(
    private val title: String
) : Screen.Replace(ReviewLessonContentFragment::class.java) {

    override fun showTitle(actionBar: ActionBar) {
        actionBar.title = title
    }
}
