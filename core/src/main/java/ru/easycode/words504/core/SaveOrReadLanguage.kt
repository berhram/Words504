package ru.easycode.words504.core

import ru.easycode.words504.data.ObjectStorage

interface SaveOrReadLanguage {
    interface Read {
        fun <T : Any> read(default: T): T
    }

    interface Save {
        fun save(obj: Any)
    }

    interface Mutable : Read, Save

    class Base(private val objectStorage: ObjectStorage) : Mutable {
        override fun <T : Any> read(default: T): T = objectStorage.read(LANGUAGE_KEY, default)
        override fun save(obj: Any) = objectStorage.save(LANGUAGE_KEY, obj)

        companion object {
            private const val LANGUAGE_KEY = "ChosenLanguageKey"
        }
    }
}
