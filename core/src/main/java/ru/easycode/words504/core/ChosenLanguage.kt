package ru.easycode.words504.core

import ru.easycode.words504.data.ObjectStorage

interface ChosenLanguage : ObjectStorage {

    class Base(private val objectStorage: ObjectStorage) : ChosenLanguage {
        override fun save(key: String, obj: Any) = objectStorage.save(key, obj)

        override fun <T : Any> read(key: String, default: T): T = objectStorage.read(key, default)
    }
}