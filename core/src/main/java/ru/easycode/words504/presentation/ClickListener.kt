package ru.easycode.words504.presentation

interface ClickListener<T: Any> {

    fun click(item: T)
}