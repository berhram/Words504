package ru.easycode.words504.presentation

interface SaveAndRestore<T: Any> {
    fun save(obj: T)
    fun restore(): T
}
