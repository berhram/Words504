package ru.easycode.words504.core

import ru.easycode.words504.data.cache.storage.ObjectStorage


interface ChosenLanguageCache {
    interface Read : ru.easycode.words504.core.Read<LanguageCache>
    interface Save : ru.easycode.words504.core.Save<LanguageCache>
    interface Mutable : Read, Save

    class Base(private val objectStorage: ObjectStorage) : Mutable {
        override fun read(): LanguageCache {
            val defaultLanguage = LanguageCache.Base("", "")
            return objectStorage.read(LANGUAGE_KEY, defaultLanguage)
        }

        override fun save(data: LanguageCache) = objectStorage.save(LANGUAGE_KEY, data)

        companion object {
            private const val LANGUAGE_KEY = "ChosenLanguageKey"
        }
    }
}
