package ru.easycode.words504

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.easycode.words504.initial.presentation.InitialScreen
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.Screen

interface MainViewModel {

    fun init()

    class Base(private val navigation: NavigationCommunication.Mutable) :
        ViewModel(),
        MainViewModel,
        Communication.Observe<Screen> {

        override fun init() = navigation.map(InitialScreen)

        override fun observe(owner: LifecycleOwner, observer: Observer<Screen>) =
            navigation.observe(owner, observer)
    }
}
