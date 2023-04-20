package ru.easycode.words504.languages.data.cache

import java.io.Serializable
import ru.easycode.words504.data.Empty

interface LanguageCache : Serializable, Empty {

    interface Mapper<T> {
        fun map(key: String, name: String): T
    }

    fun <T> map(mapper: Mapper<T>): T

    fun same(otherKey: String): Boolean

    fun same(other: LanguageCache): Boolean

    data class Base(private val key: String, private val name: String) : LanguageCache {

        override fun isEmpty() = key.isEmpty() || name.isEmpty()

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(key, name)

        override fun same(otherKey: String) = key == otherKey

        override fun same(other: LanguageCache) = this == other
    }
}
