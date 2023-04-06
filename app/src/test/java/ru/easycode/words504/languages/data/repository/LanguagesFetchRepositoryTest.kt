package ru.easycode.words504.languages.data.repository

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cloud.LanguageCloud
import ru.easycode.words504.languages.data.cloud.LanguageCloudCacheMapper
import ru.easycode.words504.languages.data.cloud.LanguagesCloudDataSource

class LanguagesFetchRepositoryTest: LanguagesRepositoryBaseTest() {

    private lateinit var repository: LanguagesFetchRepository
    private lateinit var cacheDataSource: FakeCacheDataSource
    private lateinit var cloudDataSource: FakeLanguagesCloudDataSource

    @Before
    fun setUp() {
        cacheDataSource = FakeCacheDataSource()
        cloudDataSource = FakeLanguagesCloudDataSource()
        repository = LanguagesFetchRepository(cacheDataSource, cloudDataSource, LanguageCloudCacheMapper())
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

    private class FakeLanguagesCloudDataSource : LanguagesCloudDataSource {

        private val data = mutableListOf<LanguageCloud>()

        fun changeExpectedData(expected: List<LanguageCloud>) = with(data) {
            clear()
            addAll(expected)
        }

        override suspend fun languages(): List<LanguageCloud> = data
    }
}
