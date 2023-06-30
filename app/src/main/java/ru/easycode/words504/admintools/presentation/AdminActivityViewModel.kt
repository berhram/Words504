package ru.easycode.words504.admintools.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.Navigate
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

interface AdminActivityViewModel : Navigate {

    class Base(
        private val navigationCommunication: NavigationCommunication.Mutable
    ) : ViewModel(), Communication.Observe<Screen>, AdminActivityViewModel {

        override fun observe(owner: LifecycleOwner, observer: Observer<Screen>) {
            navigationCommunication.observe(owner, observer)
        }

        override fun navigate(screen: Screen) {
            navigationCommunication.map(screen)
        }
    }
}
