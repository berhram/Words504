package ru.easycode.words504.languages.data.cache

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.BaseTest

class ChosenLanguageCacheTest : BaseTest() {
    private val objectStorage = FakeObjectStorage()
    private val chosenLanguageCache = ChosenLanguageCache.Base(objectStorage)
    private val emptyLanguage = LanguageCache.Base("", "")
    private val testLanguage = LanguageCache.Base(FAKE_KEY, "en")

    @Test
    fun `empty data`() {
        val actual = chosenLanguageCache.read().isEmpty()
        val excepted = true
        assertEquals(excepted, actual)
    }

    @Test
    fun `save language`() {
        chosenLanguageCache.save(testLanguage)
        val actual = objectStorage.read(KEY, emptyLanguage)
        val expected = testLanguage
        assertEquals(expected, actual)
    }

    @Test
    fun `read language`() {
        chosenLanguageCache.save(testLanguage)
        val actual = chosenLanguageCache.read()
        val expected = testLanguage
        assertEquals(expected, actual)
    }

    companion object {
        private const val KEY: String = "ChosenLanguageKey"
        private const val FAKE_KEY = "fake key"
    }
}
