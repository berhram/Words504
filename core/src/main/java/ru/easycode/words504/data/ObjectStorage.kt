package ru.easycode.words504.data

interface ObjectStorage {

    fun save(key: String, src: Any)

    fun <T> read(key: String, default: String, clazz: Class<T>): T

    class Base(
        private val serialization: Serialization,
        private val stringStorage: StringStorage,
    ) : ObjectStorage {

        override fun save(key: String, src: Any) {
            val json = serialization.toJson(src)
            stringStorage.save(key, json)
        }

        override fun <T> read(key: String, default: String, clazz: Class<T>): T {
            val json = stringStorage.read(key, default)
            return serialization.fromJson(json, clazz)
        }
    }
}
