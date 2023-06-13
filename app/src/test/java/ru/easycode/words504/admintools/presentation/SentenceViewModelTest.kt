package ru.easycode.words504.admintools.presentation

import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.SaveAndRestore
import ru.easycode.words504.presentation.Screen

class SentenceViewModelTest {

    @Test
    fun `test initial`() {
        val saveAndRestore = FakeSaveAndRestore()
        val sentenceUiCache = SentenceUiCache.Base()
        val navigation = FakeNavigation.Base()
        val communication = FakeCommunication.Base()

        val viewModel = SentenceViewModel.Base(
            sentenceUiCache,
            navigation,
            communication
        )

        viewModel.init(saveAndRestore)
        assertEquals(true, communication.same(sentenceUiCache.read()))

        viewModel.save(
            saveAndRestore,
            SentenceUi.Base(
                "Hello world",
                listOf(
                    WordUi.Base("hello", 0, "hello"),
                    WordUi.Base("word", 6, "word")
                )
            )
        )
        val navigationNew = FakeNavigation.Base()
        val communicationNew = FakeCommunication.Base()
        val viewModelNew = SentenceViewModel.Base(
            sentenceUiCache,
            navigationNew,
            communicationNew
        )

        viewModelNew.init(saveAndRestore)
        assertEquals(true, communicationNew.same(saveAndRestore.restore()))

        viewModelNew.save(
            SentenceUi.Base(
                "Kotlin",
                listOf(
                    WordUi.Base("Kotlin", 0, "kotlin")
                )
            )
        )
        val expected = SentenceUi.Base(
            "Kotlin",
            listOf(
                WordUi.Base("Kotlin", 0, "kotlin")
            )
        )
        assertEquals(expected, sentenceUiCache.read())
    }

    @Test
    fun `test navigation back`() {
        val sentenceUiCache = SentenceUiCache.Base()
        val navigation = FakeNavigation.Base()
        val communication = FakeCommunication.Base()

        val viewModel = SentenceViewModel.Base(
            sentenceUiCache,
            navigation,
            communication
        )

        viewModel.goBack()
        val expected = Screen.Pop
        assertEquals(true, navigation.same(expected))
    }

    private class FakeSaveAndRestore : SaveAndRestore<SentenceUi> {

        private var cache: SentenceUi = SentenceUi.Base("", emptyList())
        private var isEmpty = true

        override fun save(obj: SentenceUi) {
            cache = obj
            isEmpty = false
        }

        override fun restore(): SentenceUi = cache

        override fun isEmpty(): Boolean = isEmpty
    }

    private interface FakeCommunication : AdminSentenceCommunication {

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

        class Base : FakeNavigation {
            private var screen: Screen = ScreenEmpty()

            override fun same(other: Screen): Boolean = screen == other

            override fun map(source: Screen) {
                screen = source
            }
        }
    }

    private class ScreenEmpty : Screen {
        override fun navigate(manager: FragmentManager, containerId: Int) = Unit
        override fun showTitle(actionBar: ActionBar) = Unit
    }
}
