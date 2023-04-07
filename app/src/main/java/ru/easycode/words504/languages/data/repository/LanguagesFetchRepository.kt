package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.languages.data.cloud.LanguageCloud
import ru.easycode.words504.languages.data.cloud.LanguagesCloudDataSource
import ru.easycode.words504.languages.domain.LanguagesRepository

class LanguagesFetchRepository(
    private val cacheDataSource: LanguagesCacheDataSource.Save,
    private val cloudDataSource: LanguagesCloudDataSource,
    private val cacheMapper: LanguageCloud.Mapper<LanguageCache>
) : LanguagesRepository.Fetch {

    override suspend fun fetch() {
        val cloudLanguages = cloudDataSource.languages()
            .filter { !it.isLanguageEnglish() }
        val cacheLanguages = cloudLanguages.map { it.map(cacheMapper) }
        cacheDataSource.save(cacheLanguages)
    }
}
