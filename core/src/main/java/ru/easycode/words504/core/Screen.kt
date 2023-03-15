package ru.easycode.words504.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun replace(manager: FragmentManager)

    abstract class Base(private val containerId: Int, private val fragment: Fragment) : Screen {

        override fun replace(manager: FragmentManager) {
            manager.beginTransaction().replace(containerId, fragment).commit()
        }

    }
}
