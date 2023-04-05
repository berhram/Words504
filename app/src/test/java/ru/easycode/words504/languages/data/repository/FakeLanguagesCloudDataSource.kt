package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cloud.LanguageCloud
import ru.easycode.words504.languages.data.cloud.LanguagesCloudDataSource

class FakeLanguagesCloudDataSource : LanguagesCloudDataSource {

    private val data = mutableListOf<LanguageCloud>()

    fun changeExpectedData(expected: List<LanguageCloud>) = with(data) {
        clear()
        addAll(expected)
    }

    override suspend fun languages(): List<LanguageCloud> = data
}
