package ru.easycode.words504.languages.data.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cloud.LanguageCloud

class LanguagesFetchRepositoryTest {

    private lateinit var repository: LanguagesFetchRepository
    private lateinit var cacheDataSource: FakeCacheDataSource
    private lateinit var cloudDataSource: FakeLanguagesCloudDataSource
    private lateinit var cacheMapper: FakeCacheMapper

    @Before
    fun setUp() {
        cacheDataSource = FakeCacheDataSource()
        cloudDataSource = FakeLanguagesCloudDataSource()
        cacheMapper = FakeCacheMapper()
        repository = LanguagesFetchRepository(cacheDataSource, cloudDataSource, cacheMapper)
    }

    @Test
    fun `test first run fetch and save`() = runBlocking {
        cloudDataSource.changeExpectedData(
            listOf(
                LanguageCloud.Base("ru", "Russian"),
                LanguageCloud.Base("ch", "Chines")
            )
        )
        assertEquals(0, cacheDataSource.data.size)
        repository.fetch()

        val expected = listOf(
            LanguageCache.Base("ru", "Russian"),
            LanguageCache.Base("ch", "Chines")
        )
        assertEquals(2, cacheDataSource.data.size)
        assertEquals(expected, cacheDataSource.data)
    }
}
