package ru.easycode.words504.admintools.presentation

import ru.easycode.words504.data.Empty

interface SentenceUi: Empty {

    interface Mapper<T> {
        fun map(ui: String, words: List<WordUi>): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val ui: String,
        private val words: List<WordUi>
    ) : SentenceUi {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(ui, words)

        override fun isEmpty(): Boolean = words.isEmpty()
    }
}
