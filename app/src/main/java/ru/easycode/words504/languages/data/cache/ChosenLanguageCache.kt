package ru.easycode.words504.languages.data.cache

import ru.easycode.words504.data.cache.storage.ObjectStorage

interface ChosenLanguageCache {
    interface Read : ru.easycode.words504.data.Read<LanguageCache>
    interface Save : ru.easycode.words504.data.Save<LanguageCache>
    interface Mutable : Read, Save

    class Base(
        private val objectStorage: ObjectStorage,
        private val languageKey: String = "ChosenLanguageKey"
    ) : Mutable {
        override fun read(): LanguageCache {
            val defaultLanguage = LanguageCache.Base("", "")
            return objectStorage.read(languageKey, defaultLanguage)
        }

        override fun save(data: LanguageCache) = objectStorage.save(languageKey, data)
    }
}
