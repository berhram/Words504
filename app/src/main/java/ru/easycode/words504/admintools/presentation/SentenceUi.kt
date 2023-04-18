package ru.easycode.words504.admintools.presentation

interface SentenceUi {

    interface Mapper<T> {
        fun map(ui: String, words: List<WordUi>): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val ui: String,
        private val words: List<WordUi>
    ) : SentenceUi {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(ui, words)
    }
}
