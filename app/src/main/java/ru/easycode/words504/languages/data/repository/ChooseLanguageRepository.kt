package ru.easycode.words504.languages.data.repository

import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource

interface ChooseLanguageRepository {

    fun languages(): List<LanguageCache>
    fun saveUserChoice(languageCache: LanguageCache)
    fun userChoice(): LanguageCache
    fun save()

    class Base(
        private val languagesCacheDataSource: LanguagesCacheDataSource.Read,
        private val userChoice: ChosenLanguageCache.Mutable,
        private val chosenLanguage: ChosenLanguageCache.Save
    ) : ChooseLanguageRepository {

        override fun languages(): List<LanguageCache> = languagesCacheDataSource.read()

        override fun saveUserChoice(languageCache: LanguageCache) = userChoice.save(languageCache)

        override fun userChoice(): LanguageCache = userChoice.read()

        override fun save() = chosenLanguage.save(userChoice())
    }
}
