package ru.easycode.words504.admintools.data.cache

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.admintools.data.SentenceData
import ru.easycode.words504.admintools.data.WordData

class SentenceCacheTest : BaseTest() {

    private val objectStorage = FakeObjectStorage()
    private val sentenceCache = SentenceCache.Base(objectStorage, KEY)

    @Test
    fun `empty data`() {
        val actual = sentenceCache.read().isEmpty()
        val excepted = true
        assertEquals(excepted, actual)
    }

    @Test
    fun `save sentence`() {
        sentenceCache.save(
            SentenceData.Base(
                "hello world",
                listOf(
                    WordData.Base("hello", 0, "hello"),
                    WordData.Base("world", 7, "world")
                )
            )
        )
        val actual = objectStorage.read(KEY, SentenceData.Base("", emptyList()))
        val expected = SentenceData.Base(
            "hello world",
            listOf(
                WordData.Base("hello", 0, "hello"),
                WordData.Base("world", 7, "world")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `read sentence`() {
        sentenceCache.save(
            SentenceData.Base(
                "hello world",
                listOf(
                    WordData.Base("hello", 0, "hello"),
                    WordData.Base("world", 7, "world")
                )
            )
        )
        val actual = sentenceCache.read()
        val expected = SentenceData.Base(
            "hello world",
            listOf(
                WordData.Base("hello", 0, "hello"),
                WordData.Base("world", 7, "world")
            )
        )
        assertEquals(expected, actual)
    }

    companion object {
        private const val KEY = "sentenceKey"
    }
}
