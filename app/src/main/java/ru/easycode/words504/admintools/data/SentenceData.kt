package ru.easycode.words504.admintools.data

import ru.easycode.words504.data.Empty

interface SentenceData: Empty {
    interface Mapper<T : Any> {
        fun map(ui: String, words: List<WordData>): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        private val ui: String,
        private val words: List<WordData>
    ): SentenceData {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(ui, words)

        override fun isEmpty(): Boolean = words.isEmpty()
    }
}
