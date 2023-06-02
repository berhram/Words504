package ru.easycode.words504.data

interface Empty {
    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean = !isEmpty()
}
