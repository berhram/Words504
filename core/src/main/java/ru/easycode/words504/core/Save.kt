package ru.easycode.words504.core

interface Save<T : Any> {

    fun save(data: T)
}
