package ru.easycode.words504.presentation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersList {

    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher

    class Base : DispatchersList {
        override fun io(): CoroutineDispatcher = Dispatchers.IO
        override fun ui(): CoroutineDispatcher = Dispatchers.Main
    }
}
