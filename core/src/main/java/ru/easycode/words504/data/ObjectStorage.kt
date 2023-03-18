package ru.easycode.words504.data

interface ObjectStorage {

    fun save(key: String, obj: Any)

    fun <T : Any> read(key: String, default: T): T

    class Base(
        private val serialization: Serialization,
        private val stringStorage: StringStorage,
    ) : ObjectStorage {

        override fun save(key: String, obj: Any) {
            val json = serialization.toJson(obj)
            stringStorage.save(key, json)
        }

        override fun <T : Any> read(key: String, default: T): T {
            val defaultJson = serialization.toJson(default as Any)
            val json = stringStorage.read(key, defaultJson)
            return serialization.fromJson(json, default::class.java)
        }
    }
}
