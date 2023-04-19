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
        navigation = FakeNavigation.Base()
        communication = FakeCommunication.Base()
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
        assertEquals(true, communication.same(expected))
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
        assertEquals(true, communication.same(expected))
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
        assertEquals(true, navigation.same(expected))
    }

    private class FakeSaveAndRestore : SaveAndRestore<SentenceUi> {

        private var cache: SentenceUi = SentenceUi.Base("", emptyList())

        override fun save(obj: SentenceUi) {
            cache = obj
        }

        override fun restore(): SentenceUi = cache
    }

    interface FakeCommunication : Communication.Mutable<SentenceUi> {

        fun same(other: SentenceUi): Boolean

        class Base : FakeCommunication {

            private var sentence: SentenceUi = SentenceUi.Base("", emptyList())
            override fun same(other: SentenceUi) = sentence == other

            override fun map(source: SentenceUi) {
                sentence = source
            }
        }
    }

    private interface FakeNavigation : NavigationCommunication.Mutable {
        fun same(other: Screen): Boolean

        class Base: FakeNavigation {
            private var screen: Screen = ScreenEmpty()

            override fun same(other: Screen): Boolean = screen == other

            override fun map(source: Screen) {
                screen = source
            }
        }
    }

    private class ScreenEmpty : Screen {
        override fun navigate(manager: FragmentManager, containerId: Int) {}
    }
}
