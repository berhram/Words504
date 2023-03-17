package ru.easycode.words504.data

interface ObjectStorage {

    fun save(key: String, src: Any)

    fun <T> read(key: String, default: T, clazz: Class<T>): T

    class Base(
        private val serialization: Serialization,
        private val stringStorage: StringStorage,
    ) : ObjectStorage {

        override fun save(key: String, src: Any) {
            val json = serialization.toJson(src)
            stringStorage.save(key, json)
        }

        override fun <T> read(key: String, default: T, clazz: Class<T>): T {
            return try {
                val json = stringStorage.read(key, default.toString())
                serialization.fromJson(json, clazz)
            } catch (e: Exception) {
                default
            }
        }
    }
}
