package ru.easycode.words504.domain

interface HandleError<S : Any, E : Any> {
    fun handle(source: S): E
}
