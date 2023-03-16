package ru.easycode.words504

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import ru.easycode.words504.presentation.DispatchersList

abstract class BaseTest {
    protected class TestDispatchersList : DispatchersList {
        override fun io(): CoroutineDispatcher = StandardTestDispatcher()

        override fun ui(): CoroutineDispatcher = StandardTestDispatcher()
    }
}
