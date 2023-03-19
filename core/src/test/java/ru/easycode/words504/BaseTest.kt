package ru.easycode.words504

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import ru.easycode.words504.data.ObjectStorage
import ru.easycode.words504.data.SimpleStorage
import ru.easycode.words504.presentation.DispatchersList

abstract class BaseTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    protected class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher(),
    ) : DispatchersList {
        override fun io(): CoroutineDispatcher = dispatcher

        override fun ui(): CoroutineDispatcher = dispatcher
    }

    protected class FakeSimpleStorage : SimpleStorage {
        private var map = mutableMapOf<String, String>()
        override fun read(key: String, default: String): String =
            map.getOrDefault(key, default)

        override fun save(key: String, value: String) {
            map[key] = value
        }
    }

    protected class FakeObjectStorage : ObjectStorage {
        private var map = mutableMapOf<Any, Any>()

        override fun save(key: String, obj: Any) {
            map[key] = obj
        }

        override fun <T : Any> read(key: String, default: T): T =
            map.getOrDefault(key, default) as T
    }
}
