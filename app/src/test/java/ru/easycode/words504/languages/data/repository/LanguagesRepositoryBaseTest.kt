package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.languages.domain.LanguageDomain

abstract class LanguagesRepositoryBaseTest {

    protected class FakeCacheDataSource : LanguagesCacheDataSource.Mutable {

        val data = mutableListOf<LanguageCache>()

        fun changeExpectedData(expected: List<LanguageCache>) = with(data) {
            clear()
            addAll(expected)
        }

        override fun read(): List<LanguageCache> = data

        override fun save(data: List<LanguageCache>): Unit = with(this.data) {
            clear()
            addAll(data)
        }
    }
    protected class FakeChosenLanguageCache : ChosenLanguageCache.Mutable {

        var data: LanguageCache = LanguageCache.Base("", "")

        override fun read(): LanguageCache = data

        override fun save(data: LanguageCache) {
            this.data = data
        }
    }
    protected class FakeDomainMapper : LanguageCache.Mapper<LanguageDomain> {
        override fun map(key: String, name: String) = LanguageDomain(key, name)
    }
}
