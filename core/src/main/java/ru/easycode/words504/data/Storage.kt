package ru.easycode.words504.data

interface Storage : SimpleStorage, ObjectStorage {

    class Base(
        private val simpleStorage: SimpleStorage,
        private val objectStorage: ObjectStorage
    ) : Storage {

        override fun read(key: String, default: String): String = simpleStorage.read(key, default)

        override fun <T : Any> read(key: String, default: T): T = objectStorage.read(key, default)

        override fun save(key: String, value: String) = simpleStorage.save(key, value)

        override fun save(key: String, obj: Any) = objectStorage.save(key, obj)
    }
}
