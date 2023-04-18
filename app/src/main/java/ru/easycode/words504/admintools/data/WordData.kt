package ru.easycode.words504.admintools.data

interface WordData {

    interface Mapper<T : Any> {
        fun map(
            ui: String,
            index: Int,
            dictionaryForm: String
        ): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        private val ui: String,
        private val index: Int,
        private val dictionaryForm: String
    ): WordData {
        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(ui, index, dictionaryForm)
    }
}
