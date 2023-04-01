package ru.easycode.words504.data.cache.storage

interface StringStorage {
    fun read(key: String, default: String): String

    fun save(key: String, value: String)
}
