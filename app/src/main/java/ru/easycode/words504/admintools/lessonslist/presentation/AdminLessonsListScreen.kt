package ru.easycode.words504.admintools.lessonslist.presentation

import androidx.appcompat.app.ActionBar
import ru.easycode.words504.presentation.Screen

class AdminLessonsListScreen(private val title: String) :
    Screen.Replace(AdminLessonsListFragment::class.java) {
    override fun showTitle(actionBar: ActionBar) {
        actionBar.title = title
    }
}
