package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource

interface ChooseLanguageRepository {

    fun languages(): List<LanguageCache>
    fun userChoice(languageCache: LanguageCache)
    fun fetchUserChoice(): LanguageCache
    fun save()

    class Base(
        private val languagesCacheDataSource: LanguagesCacheDataSource.Mutable,
        private val userChoice: ChosenLanguageCache.Mutable,
        private val chosenLanguage: ChosenLanguageCache.Mutable
    ) : ChooseLanguageRepository {

        override fun languages(): List<LanguageCache> = languagesCacheDataSource.read()

        override fun userChoice(languageCache: LanguageCache) = userChoice.save(languageCache)

        override fun fetchUserChoice(): LanguageCache = userChoice.read()

        override fun save() = chosenLanguage.save(userChoice.read())
    }
}
