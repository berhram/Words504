package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel

interface DependencyContainer {
    fun <T : ViewModel> module(clazz: Class<T>): Module<*>

    class Error : DependencyContainer {

        override fun <T : ViewModel> module(clazz: Class<T>): Module<*> {
            throw IllegalStateException("no module found for $clazz")
        }
    }
}