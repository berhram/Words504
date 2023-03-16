package ru.easycode.words504.data

import com.google.gson.Gson
import java.util.Objects

interface ProvideSerializationGson {

    fun toJson(str: Any): String

    fun <T>fromJson(json:String,obj: Class<T>): T

    class Base(private val gson: Gson = Gson()): ProvideSerializationGson {
        override fun toJson(str: Any): String {
            return gson.toJson(str)
        }

        override fun <T> fromJson(json: String,obj: Class<T>): T {
          return  gson.fromJson(json,obj)
        }
    }
}

