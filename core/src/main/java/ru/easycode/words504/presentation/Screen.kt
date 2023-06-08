package ru.easycode.words504.presentation

import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.io.Serializable

interface Screen : Serializable {

    fun navigate(manager: FragmentManager, containerId: Int)
    fun showTitle(actionBar: ActionBar)

    abstract class Abstract(private val clazz: Class<out Fragment>) : Screen {
        override fun showTitle(actionBar: ActionBar) {
            actionBar.title = clazz.canonicalName ?: ""
        }
    }

    abstract class Add(private val clazz: Class<out Fragment>) : Abstract(clazz) {
        override fun navigate(manager: FragmentManager, containerId: Int) {
            manager.beginTransaction()
                .add(containerId, clazz.newInstance())
                .addToBackStack(clazz.canonicalName)
                .commit()
        }
    }

    abstract class Replace(private val clazz: Class<out Fragment>) : Abstract(clazz) {
        override fun navigate(manager: FragmentManager, containerId: Int) {
            manager.beginTransaction()
                .replace(containerId, clazz.newInstance())
                .commit()
        }
    }

    object Pop : Screen {
        override fun navigate(manager: FragmentManager, containerId: Int) {
            manager.popBackStack()
        }

        override fun showTitle(actionBar: ActionBar) = Unit
    }
}
