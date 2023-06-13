package ru.easycode.words504.admintools.reviewLessonContent.presentation.models

interface TextPreview {
    interface Mapper<T : Any> {
        fun map(title: String, text: String): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(private val title: String, private val text: String) : TextPreview {
        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(title, text)
    }
}
