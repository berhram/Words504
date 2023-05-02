package ru.easycode.words504.initial.data

import ru.easycode.words504.initial.domain.InitialRepository
import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.languages.data.cloud.LanguageCloudCacheMapper
import ru.easycode.words504.languages.data.cloud.LanguagesCloudDataSource

class BaseInitialRepository(
    private val chosenCache: ChosenLanguageCache.Read,
    private val languagesCache: LanguagesCacheDataSource.Save,
    private val languagesCloud: LanguagesCloudDataSource,
    private val languageMapper: LanguageCloudCacheMapper
) : InitialRepository {

    override fun userHasChosenLanguage(): Boolean = !chosenCache.read().isEmpty()


    override suspend fun init() {
        val allLanguages = languagesCloud.languages().map { it.map(languageMapper) }
        languagesCache.save(allLanguages)
    }
}