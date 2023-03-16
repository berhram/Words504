package ru.easycode.words504.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.easycode.words504.R

interface Screen {
    fun replace(manager: FragmentManager)

    abstract class Base(
        private val clazz: Class<Fragment>,
        private val resId: Int = R.id.container,
        ) : Screen {
        override fun replace(manager: FragmentManager) {
            manager.beginTransaction().replace(resId, clazz.newInstance())
                .commit()
        }
    }
}

