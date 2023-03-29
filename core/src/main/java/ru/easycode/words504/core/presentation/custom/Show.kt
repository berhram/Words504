package ru.easycode.words504.core.presentation.custom

interface Show<S : Any, R : Any > {
    fun show(source : S) : R
}
interface ShowView {
    fun show()
}
