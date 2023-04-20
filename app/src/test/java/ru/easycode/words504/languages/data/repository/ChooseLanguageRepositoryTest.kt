package ru.easycode.words504.languages.data.repository

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource

class ChooseLanguageRepositoryTest {

    private lateinit var repository: ChooseLanguageRepository
    private lateinit var dataSource: FakeLanguagesCacheDataSource
    private lateinit var userTempChoice: FakeChosenLanguageCache
    private lateinit var chosenLanguage: FakeChosenLanguageCache

    @Before
    fun setUp() {
        dataSource = FakeLanguagesCacheDataSource()
        userTempChoice = FakeChosenLanguageCache()
        chosenLanguage = FakeChosenLanguageCache()

        repository = ChooseLanguageRepository.Base(dataSource, userTempChoice, chosenLanguage)
    }

    @Test
    fun `test languages`() {
        dataSource.save(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )
        val expected = listOf(
            LanguageCache.Base("ru", "Russian"),
            LanguageCache.Base("ch", "Chinese")
        )
        assertEquals(expected, repository.languages())
    }

    @Test
    fun `test save user choice temp`() {
        repository.userChoice(LanguageCache.Base("ru", "Russian"))
        val expected = LanguageCache.Base("ru", "Russian")
        assertEquals(expected, userTempChoice.read())
    }

    @Test
    fun `test user choice save`() {
        userTempChoice.save(LanguageCache.Base("ru", "Russian"))
        repository.save()
        val expected = LanguageCache.Base("ru", "Russian")
        assertEquals(expected, userTempChoice.read())
    }

    @Test
    fun `test fetch user choice`() {
        userTempChoice.save(LanguageCache.Base("ru", "Russian"))
        val expected = LanguageCache.Base("ru", "Russian")
        assertEquals(expected, repository.fetchUserChoice())
    }

    private class FakeChosenLanguageCache : ChosenLanguageCache.Mutable {

        private var languageCache: LanguageCache = LanguageCache.Base("", "")

        override fun read(): LanguageCache = languageCache

        override fun save(data: LanguageCache) {
            languageCache = data
        }
    }

    private class FakeLanguagesCacheDataSource : LanguagesCacheDataSource.Mutable {
        private val list = mutableListOf<LanguageCache>()

        override fun read(): List<LanguageCache> {
            return list
        }

        override fun save(data: List<LanguageCache>) {
            list.clear()
            list.addAll(data)
        }
    }
}
