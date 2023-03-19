package ru.easycode.words504.core.data

import ru.easycode.words504.core.domain.DomainError

interface HandleError<S : Any, E : DomainError> {
    fun handle(source: S): E
}
