package ru.easycode.words504

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.core.ChosenLanguageCache
import ru.easycode.words504.core.LanguageCache

class ChosenLanguageCacheTest : BaseTest() {

    private val objectStorage = FakeObjectStorage()
    private val chosenLanguageCache = ChosenLanguageCache.Base(objectStorage)
    private val emptyModel = LanguageCache.Base("", "")
    private val testModel = LanguageCache.Base(FAKE_KEY, "en")

    @Test
    fun empty_data() {
        val actual = chosenLanguageCache.read(emptyModel).isEmpty()
        val excepted = true
        assertEquals(excepted, actual)
    }

    @Test
    fun save_language() {
        chosenLanguageCache.save(testModel)
        val actual = objectStorage.read(KEY, emptyModel)
        val expected = testModel
        assertEquals(expected, actual)
    }

    @Test
    fun read_language() {
        chosenLanguageCache.save(testModel)
        val actual = chosenLanguageCache.read(emptyModel)
        val expected = testModel
        assertEquals(expected, actual)
    }

    companion object {
        private const val FAKE_KEY = "fake key"
        private const val KEY = "ChosenLanguageKey"
    }
}
