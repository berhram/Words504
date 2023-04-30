package ru.easycode.words504

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import okhttp3.Request
import okio.Timeout
import org.junit.Assert
import retrofit2.Call
import retrofit2.Callback
import ru.easycode.words504.data.cache.storage.ObjectStorage
import ru.easycode.words504.data.cache.storage.SimpleStorage
import ru.easycode.words504.presentation.DispatchersList

abstract class BaseTest {

    protected lateinit var functionsCallsStack: FunctionsCallsStack

    open fun init() {
        functionsCallsStack = FunctionsCallsStack.Base()
    }

    protected interface FunctionsCallsStack {

        fun put(funName: String)
        fun checkCalled(funName: String)
        fun checkStack(size: Int)

        class Base : FunctionsCallsStack {
            private val list = mutableListOf<String>()
            private var count = 0

            override fun put(funName: String) {
                list.add(funName)
            }

            override fun checkCalled(funName: String) {
                Assert.assertEquals(funName, list[count++])
            }

            override fun checkStack(size: Int) {
                Assert.assertEquals(size, list.size)
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    protected class TestDispatchersList(
        private val dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher(),
    ) : DispatchersList {
        override fun io(): CoroutineDispatcher = dispatcher

        override fun ui(): CoroutineDispatcher = dispatcher
    }

    protected class FakeSimpleStorage : SimpleStorage {
        private val map = mutableMapOf<String, String>()
        override fun read(key: String, default: String): String =
            map.getOrDefault(key, default)

        override fun save(key: String, value: String) {
            map[key] = value
        }
    }

    protected class FakeObjectStorage : ObjectStorage {
        private val map = mutableMapOf<String, Any>()

        override fun save(key: String, obj: Any) {
            map[key] = obj
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : Any> read(key: String, default: T): T =
            map.getOrDefault(key, default) as T
    }

    protected abstract class FakeCall<T> : Call<T> {

        override fun clone(): Call<T> = this

        override fun enqueue(callback: Callback<T>) = Unit

        override fun isExecuted(): Boolean = true

        override fun cancel() = Unit

        override fun isCanceled(): Boolean = true

        override fun request(): Request = Request.Builder().build()

        override fun timeout(): Timeout = Timeout()
    }
}
