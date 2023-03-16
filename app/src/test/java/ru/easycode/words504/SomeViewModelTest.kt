package ru.easycode.words504

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.presentation.DispatchersList

class SomeViewModelTest : BaseTest() {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test translate`() = runTest {
        val dispatchers = TestDispatchersList()
        val viewModel = SomeViewModel(dispatchers)
        viewModel.translate("example")
        assertEquals("example-translated", viewModel.result)
    }

    private class SomeViewModel(
        private val dispatchers: DispatchersList,
    ) : ViewModel() {
        var result: String = ""

        fun translate(word: String) {
            viewModelScope.launch(dispatchers.io()) {
                withContext(dispatchers.ui()) {
                    result = "$word-translated"
                }
            }
        }
    }
}
