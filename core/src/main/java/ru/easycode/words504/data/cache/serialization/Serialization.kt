package ru.easycode.words504.data.cache.serialization

import com.google.gson.Gson

interface Serialization {

    fun toJson(src: Any): String

    fun <T> fromJson(json: String, clazz: Class<T>): T

    class Base(private val gson: Gson = Gson()) : Serialization {
        override fun toJson(src: Any): String =
            gson.toJson(src)

        override fun <T> fromJson(json: String, clazz: Class<T>): T =
            gson.fromJson(json, clazz)
    }
}
