package ru.easycode.words504.admintools.presentation

import java.io.Serializable

interface WordUi : Serializable {

    interface Mapper<T> {
        fun map(ui: String, index: Int, dictionaryForm: String): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val ui: String,
        private val index: Int,
        private val dictionaryForm: String
    ) : WordUi {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(ui, index, dictionaryForm)
    }
}
