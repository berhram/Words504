package ru.easycode.words504.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {
    fun replace(manager: FragmentManager)

    abstract class Base(private val clazz: Class<Fragment>) : Screen {

        protected abstract val resId: Int
        override fun replace(manager: FragmentManager) {
            manager.beginTransaction().replace(resId, clazz.newInstance())
                .commit()
        }
    }
}

