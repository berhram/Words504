package ru.easycode.words504.data

import com.google.gson.Gson

interface ProvideSerialization {

    fun toJson(src: Any): String

    fun <T> fromJson(json: String, obj: Class<T>): T

    class Base(private val gson: Gson = Gson()) : ProvideSerialization {
        override fun toJson(src: Any): String {
            return gson.toJson(src)
        }

        override fun <T> fromJson(json: String, obj: Class<T>): T {
            return gson.fromJson(json, obj)
        }
    }
}

