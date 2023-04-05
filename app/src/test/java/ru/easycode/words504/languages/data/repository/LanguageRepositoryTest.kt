package ru.easycode.words504.languages.data.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.domain.LanguageDomain

class LanguageRepositoryTest {

    private lateinit var repository: LanguageRepository
    private lateinit var chosenLanguageCache: FakeChosenLanguageCache
    private lateinit var domainMapper: FakeDomainMapper

    @Before
    fun setUp() {
        chosenLanguageCache = FakeChosenLanguageCache()
        domainMapper = FakeDomainMapper()
        repository = LanguageRepository(chosenLanguageCache, domainMapper)
    }

    @Test
    fun `test language`() = runBlocking {
        chosenLanguageCache.data = LanguageCache.Base("ru", "Russian")
        val expected = LanguageDomain("ru", "Russian")
        val actual = repository.language()
        assertEquals(expected, actual)
    }
}
