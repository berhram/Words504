package ru.easycode.words504.languages.data.cache

import ru.easycode.words504.data.Empty

interface LanguageCache : Empty {

    interface Mapper<T> {
        fun map(key: String, name: String): T
    }

    fun <T> map(mapper: Mapper<T>): T

    fun key(): String

    data class Base(private val key: String, private val name: String) : LanguageCache {

        override fun isEmpty() = key.isEmpty() || name.isEmpty()

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(key, name)

        override fun key(): String = key
    }
}
