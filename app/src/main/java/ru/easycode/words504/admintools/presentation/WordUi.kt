package ru.easycode.words504.admintools.presentation

data class WordUi(
    private val ui: String,
    private val index: Int,
    private val dictionaryForm: String
) {

    interface Mapper<T> {
        fun map(ui: String, index: Int, dictionaryForm: String): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(ui, index, dictionaryForm)
}
