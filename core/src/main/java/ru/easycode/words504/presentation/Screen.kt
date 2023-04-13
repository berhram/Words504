package ru.easycode.words504.presentation

import androidx.fragment.app.FragmentManager

interface Screen {
    fun replace(manager: FragmentManager)

    abstract class Base(private val navigationStrategy: NavigationStrategy) : Screen {
        override fun replace(manager: FragmentManager) {
            navigationStrategy.navigate(manager)
        }
    }
}
