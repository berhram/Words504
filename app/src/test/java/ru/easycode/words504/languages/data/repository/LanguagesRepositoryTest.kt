package ru.easycode.words504.languages.data.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguageKeyMapper
import ru.easycode.words504.languages.domain.LanguageDomain

class LanguagesRepositoryTest: LanguagesRepositoryBaseTest() {

    private lateinit var repository: LanguagesRepository
    private lateinit var cacheDataSource: FakeCacheDataSource
    private lateinit var chosenLanguageCache: FakeChosenLanguageCache
    private lateinit var domainMapper: FakeDomainMapper

    @Before
    fun setUp() {
        cacheDataSource = FakeCacheDataSource()
        chosenLanguageCache = FakeChosenLanguageCache()
        domainMapper = FakeDomainMapper()
        repository = LanguagesRepository(cacheDataSource, chosenLanguageCache, domainMapper, LanguageKeyMapper())
    }

    @Test
    fun `test languages`() = runBlocking {
        cacheDataSource.changeExpectedData(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chines")
            )
        )
        val actual = repository.languages()
        val expected = listOf(
            LanguageDomain("ru", "Russian"),
            LanguageDomain("ch", "Chines")
        )
        assertEquals(expected, actual)
    }

    @Test
    fun `test save chosen language`() = runBlocking {
        cacheDataSource.changeExpectedData(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chines")
            )
        )
        val expected = LanguageCache.Base("ch", "Chines")
        repository.save("ch")
        assertEquals(expected, chosenLanguageCache.data)
    }
}
