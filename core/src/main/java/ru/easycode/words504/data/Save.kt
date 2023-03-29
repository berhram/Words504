package ru.easycode.words504.data

interface Save<T : Any> {

    fun save(data: T)
}
