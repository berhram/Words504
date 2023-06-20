package ru.easycode.words504.admintools.initial.presentation

import androidx.lifecycle.ViewModel
import ru.easycode.words504.presentation.Navigate
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

/**
 * @author Asatryan on 02.05.2023
 */
class AdminInitialViewModel(
    private val navigation: NavigationCommunication.Update
) : ViewModel(), Navigate {

    override fun navigate(screen: Screen) = navigation.map(screen)
}
