package ru.easycode.words504

import kotlinx.coroutines.runBlocking
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
        private val dispatchers: DispatchersList
    ) : BaseViewModel(dispatchers) {

        var result: String = ""

        fun translate(word: String) =
            handle({ word }, { result = "$word-translated" })
    }
}
