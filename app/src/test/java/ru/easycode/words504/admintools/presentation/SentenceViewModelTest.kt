package ru.easycode.words504.admintools.presentation

import androidx.fragment.app.FragmentManager
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.SaveAndRestore
import ru.easycode.words504.presentation.Screen

class SentenceViewModelTest {

    private lateinit var viewModel: SentenceViewModel
    private lateinit var communication: FakeCommunication
    private lateinit var navigation: FakeNavigation
    private val sentenceUiCache = SentenceUiCache.Base()

    @Before
    fun setUp() {
        navigation = FakeNavigation()
        communication = FakeCommunication()
        viewModel = SentenceViewModel.Base(
            sentenceUiCache,
            navigation,
            communication
        )
    }

    @Test
    fun `test initial`() {
        val saveAndRestore = FakeSaveAndRestore()
        saveAndRestore.save(
            SentenceUi.Base(
                "Hello world",
                listOf(
                    WordUi.Base("hello", 0, "hello"),
                    WordUi.Base("word", 6, "word")
                )
            )
        )
        viewModel.init(saveAndRestore)
        val expected = SentenceUi.Base(
            "Hello world",
            listOf(
                WordUi.Base("hello", 0, "hello"),
                WordUi.Base("word", 6, "word")
            )
        )
        assertEquals(expected, communication.sentence)
    }

    @Test
    fun `test sentence`() {
        viewModel.sentence(
            SentenceUi.Base(
                "Hello world",
                listOf(
                    WordUi.Base("hello", 0, "hello"),
                    WordUi.Base("word", 6, "word")
                )
            )
        )
        val expected = SentenceUi.Base(
            "Hello world",
            listOf(
                WordUi.Base("hello", 0, "hello"),
                WordUi.Base("word", 6, "word")
            )
        )
        assertEquals(expected, communication.sentence)
    }

    @Test
    fun `test save`() {
        viewModel.sentence(
            SentenceUi.Base(
                "Hello world",
                listOf(
                    WordUi.Base("hello", 0, "hello"),
                    WordUi.Base("word", 6, "word")
                )
            )
        )
        viewModel.save()

        val expected = SentenceUi.Base(
            "Hello world",
            listOf(
                WordUi.Base("hello", 0, "hello"),
                WordUi.Base("word", 6, "word")
            )
        )
        assertEquals(expected, sentenceUiCache.read())
    }

    @Test
    fun `test navigation back`() {
        viewModel.backPressed()
        val expected = Screen.Pop
        assertEquals(expected, navigation.screen)
    }

    private class FakeSaveAndRestore : SaveAndRestore<SentenceUi> {

        private var cache: SentenceUi = SentenceUi.Base("", emptyList())

        override fun save(obj: SentenceUi) {
            cache = obj
        }

        override fun restore(): SentenceUi = cache
    }

    private class FakeCommunication : Communication.Mutable<SentenceUi> {
        var sentence: SentenceUi = SentenceUi.Base("", emptyList())

        override fun map(source: SentenceUi) {
            sentence = source
        }
    }

    private class FakeNavigation : NavigationCommunication.Mutable {
        var screen: Screen = ScreenEmpty()

        override fun map(source: Screen) {
            screen = source
        }
    }

    private class ScreenEmpty : Screen {
        override fun navigate(manager: FragmentManager, containerId: Int) {}
    }
}
