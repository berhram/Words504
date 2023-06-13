package ru.easycode.words504.admintools.reviewLessonContent.presentation.models

interface WordsPreview {
    interface Mapper<T : Any> {
        fun map(words: List<String>): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(private val words: List<String>) : WordsPreview {
        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(words)
    }
}
