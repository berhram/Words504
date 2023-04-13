package ru.easycode.words504.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface NavigationStrategy {

    fun navigate(manager: FragmentManager)

    abstract class Replace(
        private val clazz: Class<out Fragment>,
        private val containerId: Int
    ) : NavigationStrategy {
        override fun navigate(manager: FragmentManager) {
            manager.beginTransaction()
                .replace(containerId, clazz.newInstance())
                .commit()
        }
    }

    abstract class Add(
        private val clazz: Class<out Fragment>,
        private val containerId: Int
    ) : NavigationStrategy {
        override fun navigate(manager: FragmentManager) {
            manager.beginTransaction()
                .add(containerId, clazz.newInstance())
                .addToBackStack(clazz.simpleName)
                .commit()
        }
    }

    class Pop : NavigationStrategy {
        override fun navigate(manager: FragmentManager) {
            manager.popBackStack()
        }
    }
}
