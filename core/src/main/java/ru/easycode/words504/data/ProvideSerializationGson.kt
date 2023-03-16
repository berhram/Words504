package ru.easycode.words504.data

interface ProvideSerializationGson {

    fun toJson(str: Any): String

    fun <T>toString(json:String, obj: T): T

    class Base(): ProvideSerializationGson {
        override fun toJson(str: Any): String {
            TODO("Not yet implemented")
        }

        override fun <T> toString(json: String, obj: T): T {
            TODO("Not yet implemented")
        }
    }
}