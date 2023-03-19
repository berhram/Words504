package ru.easycode.words504

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import ru.easycode.words504.presentation.DispatchersList

abstract class BaseTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    protected class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher(),
    ) : DispatchersList {
        override fun io(): CoroutineDispatcher = dispatcher

        override fun ui(): CoroutineDispatcher = dispatcher
    }

    protected class TestMap() {
        var testMap = mutableMapOf<Any, Any>("" to "")
    }

    protected class TestHashMap() {
        var testHashMap = hashMapOf("" to "")
    }
}
