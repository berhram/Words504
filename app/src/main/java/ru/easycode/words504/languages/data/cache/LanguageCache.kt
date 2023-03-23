package ru.easycode.words504.languages.data.cache

import ru.easycode.words504.core.Empty

interface LanguageCache : Empty {
    data class Base(private val key: String, private val name: String) : LanguageCache {
        override fun isEmpty() = key.isEmpty() || name.isEmpty()
    }
}
