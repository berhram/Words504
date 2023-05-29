package ru.easycode.words504.data.cache

interface DataBase<T : Any> {
    fun create(): T
    fun <K : Any> create(typeConverter: K): T
}
