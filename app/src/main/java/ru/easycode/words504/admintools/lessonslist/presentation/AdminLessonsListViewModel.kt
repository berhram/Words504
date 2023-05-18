package ru.easycode.words504.admintools.lessonslist.presentation

import androidx.lifecycle.ViewModel
import ru.easycode.words504.admintools.presentation.AdminNavigate
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

class AdminLessonsListViewModel(
    private val navigation: NavigationCommunication.Update
) : ViewModel(), AdminNavigate {

    override fun navigate(screen: Screen) = navigation.map(screen)
}
