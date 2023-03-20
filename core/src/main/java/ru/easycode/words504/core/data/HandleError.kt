package ru.easycode.words504.core.data

interface HandleError<S : Any, E : Any> {
    fun handle(source: S): E
}
