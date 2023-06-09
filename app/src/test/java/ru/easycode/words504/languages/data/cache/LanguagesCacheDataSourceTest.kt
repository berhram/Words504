package ru.easycode.words504.languages.data.cache

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.BaseTest

class LanguagesCacheDataSourceTest : BaseTest() {

    private val cacheDataSource = LanguagesCacheDataSource.Base(FakeObjectStorage())

    @Test
    fun `test read empty`() = runBlocking {
        val expected = emptyList<LanguageCache>()
        val actual = cacheDataSource.read()
        assertEquals(expected, actual)
    }

    @Test
    fun `test save and read not empty`() {
        cacheDataSource.save(
            listOf(
                LanguageCache.Base("ru", "russian"),
                LanguageCache.Base("ch", "chinese")
            )
        )
        val expected = listOf<LanguageCache>(
            LanguageCache.Base("ru", "russian"),
            LanguageCache.Base("ch", "chinese")
        )

        val actual = cacheDataSource.read()
        assertEquals(expected, actual)
    }
}
