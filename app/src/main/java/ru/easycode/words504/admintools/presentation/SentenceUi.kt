package ru.easycode.words504.admintools.presentation

data class SentenceUi(
    private val ui: String,
    private val words: List<WordUi>
) {
    interface Mapper<T> {
        fun map(ui: String, words: List<WordUi>): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(ui, words)
}
