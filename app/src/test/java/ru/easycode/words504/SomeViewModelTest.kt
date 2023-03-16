package ru.easycode.words504

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import ru.easycode.words504.presentation.DispatchersList

class SomeViewModelTest : BaseTest() {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test translate`() = runTest {
        val dispatchers = TestDispatchersList()
        val viewModel = SomeViewModel(dispatchers)
        viewModel.translate("example")
        advanceUntilIdle()
        assertEquals("example-translated", viewModel.result)
    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val dispatcher: TestDispatcher = StandardTestDispatcher(),
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

class SomeViewModel(
    private val dispatchers: DispatchersList,
) : ViewModel() {
    var result: String = ""

    fun translate(word: String) {
        viewModelScope.launch(dispatchers.io()) {
            delay(2000)
            result = "$word-translated"
        }
    }
}
