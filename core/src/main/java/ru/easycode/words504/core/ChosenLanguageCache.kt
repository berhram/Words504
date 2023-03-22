package ru.easycode.words504.core

import ru.easycode.words504.data.ObjectStorage

interface ChosenLanguageCache {
    interface Read {
        fun read(): LanguageCache
    }

    interface Save {
        fun save(language: LanguageCache)
    }

    interface Mutable : Read, Save

    class Base(private val objectStorage: ObjectStorage) : Mutable {
        override fun read(): LanguageCache {
            val defaultLanguage = LanguageCache.Base("", "")
            return objectStorage.read(LANGUAGE_KEY, defaultLanguage)
        }

        override fun save(language: LanguageCache) = objectStorage.save(LANGUAGE_KEY, language)

        companion object {
            private const val LANGUAGE_KEY = "ChosenLanguageKey"
        }
    }
}
