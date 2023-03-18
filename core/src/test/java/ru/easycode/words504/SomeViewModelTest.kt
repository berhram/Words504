package ru.easycode.words504

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.core.presentation.BaseViewModel
import ru.easycode.words504.presentation.DispatchersList

class SomeViewModelTest : BaseTest() {

    @Test
    fun `test translate`() = runBlocking {
        val dispatchers = TestDispatchersList()
        val viewModel = SomeViewModel(dispatchers)
        viewModel.translate("example")
        assertEquals("example-translated", viewModel.result)
    }

    private class SomeViewModel(
        private val dispatchers: DispatchersList,
    ) : BaseViewModel(dispatchers) {

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
