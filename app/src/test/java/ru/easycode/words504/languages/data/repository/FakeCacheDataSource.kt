package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource

class FakeCacheDataSource : LanguagesCacheDataSource.Mutable {

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
