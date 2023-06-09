package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguageKeyMapper
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.languages.domain.LanguageDomain
import ru.easycode.words504.languages.domain.LanguagesRepository

class LanguagesRepository(
    private val cacheDataSource: LanguagesCacheDataSource.Read,
    private val chosenLanguageCache: ChosenLanguageCache.Save,
    private val domainMapper: LanguageCache.Mapper<LanguageDomain>
) : LanguagesRepository.Languages {

    override suspend fun languages(): List<LanguageDomain> = cacheDataSource.read()
        .map { it.map(domainMapper) }

    override suspend fun save(languageCode: String) {
        val cacheLanguage = cacheDataSource.read()
            .find { it.map(LanguageKeyMapper.SameLanguage(languageCode)) }
        cacheLanguage?.let {
            chosenLanguageCache.save(it)
        }
    }
}
