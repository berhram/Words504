package ru.easycode.words504.core

import ru.easycode.words504.data.ObjectStorage

interface ChosenLanguageCache {
    interface Read {
        fun read(default: LanguageCache): LanguageCache
    }

    interface Save {
        fun save(language: LanguageCache)
    }

    interface Mutable : Read, Save

    class Base(private val objectStorage: ObjectStorage) : Mutable {
        override fun read(default: LanguageCache) = objectStorage.read(LANGUAGE_KEY, default)
        override fun save(language: LanguageCache) = objectStorage.save(LANGUAGE_KEY, language)

        companion object {
            private const val LANGUAGE_KEY = "ChosenLanguageKey"
        }
    }
}
