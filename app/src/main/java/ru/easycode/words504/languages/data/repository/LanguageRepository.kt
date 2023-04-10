package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.domain.LanguageDomain
import ru.easycode.words504.languages.domain.LanguagesRepository

class LanguageRepository(
    private val chosenLanguageCache: ChosenLanguageCache.Read,
    private val domainMapper: LanguageCache.Mapper<LanguageDomain>
) : LanguagesRepository.ChosenLanguage {

    override suspend fun language(): LanguageDomain = chosenLanguageCache.read()
        .map(domainMapper)
}
