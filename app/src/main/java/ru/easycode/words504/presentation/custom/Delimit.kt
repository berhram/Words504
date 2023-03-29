package ru.easycode.words504.presentation.custom

interface Delimit {

    fun delimit(delimiter: Char): Boolean

    abstract class Abstract : Delimit {
        override fun delimit(delimiter: Char): Boolean = delimiter in LIST_OF_DELIMITERS
        companion object {
            private val LIST_OF_DELIMITERS = listOf(' ', '!', '?', '.', ',', ';')
        }
    }
}
