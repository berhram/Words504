package ru.easycode.words504.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {
    fun replace(manager: FragmentManager)

    abstract class Base(
        private val clazz: Class<out Fragment>,
        private val resId: Int = com.google.android.material.R.id.container
    ) : Screen {
        override fun replace(manager: FragmentManager) {
            manager.beginTransaction().replace(resId, clazz.newInstance()).commit()
        }
    }
}

