package ru.easycode.words504.core

interface LanguageCache : Empty {
    data class Base(private val key: String, private val name: String) : LanguageCache {
        override fun isEmpty() = key.isEmpty() || name.isEmpty()
    }
}
