package ru.easycode.words504.data.cache.storage

import com.google.gson.reflect.TypeToken
import ru.easycode.words504.data.cache.serialization.Serialization

interface ObjectStorage {

    fun save(key: String, obj: Any)

    fun <T : Any> read(key: String, default: T): T

    fun <T : Any> readList(key: String, typeToken: TypeToken<T>): T

    class Base(
        private val serialization: Serialization,
        private val stringStorage: StringStorage
    ) : ObjectStorage {

        override fun save(key: String, obj: Any) {
            val json = serialization.toJson(obj)
            stringStorage.save(key, json)
        }

        override fun <T : Any> read(key: String, default: T): T {
            val defaultJson = serialization.toJson(default)
            val json = stringStorage.read(key, defaultJson)
            return serialization.fromJson(json, default::class.java)
        }

        override fun <T : Any> readList(key: String, typeToken: TypeToken<T>): T {
            val default = serialization.toJson(listOf<T>())
            val json = stringStorage.read(key, default)
            return serialization.fromJsonList(json, typeToken)
        }
    }
}
