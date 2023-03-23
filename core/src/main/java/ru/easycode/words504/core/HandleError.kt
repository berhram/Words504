package ru.easycode.words504.core

interface HandleError<S : Any, E : Any> {
    fun handle(source: S): E
}
