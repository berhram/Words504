package ru.easycode.words504.data

interface ObjectStorage {

    fun save(key: String, obj: Any)

    fun <T> read(key: String, default: Any, clazz: Class<T>): T

    class Base(
        private val serialization: Serialization,
        private val stringStorage: StringStorage,
    ) : ObjectStorage {

        override fun save(key: String, obj: Any) {
            val json = serialization.toJson(obj)
            stringStorage.save(key, json)
        }

        override fun <T> read(key: String, default: Any, clazz: Class<T>): T {
            val defaultJson = serialization.toJson(default)
            val json = stringStorage.read(key, defaultJson)
            return serialization.fromJson(json, clazz)
        }
    }
}
