package ru.easycode.words504.admintools.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

class AdminViewModel(
    private val navigationCommunication: NavigationCommunication.Mutable,
) : ViewModel(), Communication.Observe<Screen> {

    override fun observe(owner: LifecycleOwner, observer: Observer<Screen>) {
        navigationCommunication.observe(owner, observer)
    }
}
