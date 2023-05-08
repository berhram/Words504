package ru.easycode.words504.data.cache.storage

import com.google.gson.reflect.TypeToken

interface Storage : SimpleStorage, ObjectStorage {

    class Base(
        private val simpleStorage: SimpleStorage,
        private val objectStorage: ObjectStorage
    ) : Storage {

        override fun read(key: String, default: String): String = simpleStorage.read(key, default)

        override fun <T : Any> read(key: String, default: T): T = objectStorage.read(key, default)

        override fun save(key: String, value: String) = simpleStorage.save(key, value)

        override fun save(key: String, obj: Any) = objectStorage.save(key, obj)

        override fun <T : Any> readList(key: String, typeToken: TypeToken<T>): T =
            objectStorage.readList(key, typeToken)
    }
}
