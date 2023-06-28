package ru.easycode.words504.admintools.reviewLessonContent.presentation.models

interface QuotePreview {

    interface Mapper<T : Any> {
        fun map(quote: String, author: String): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        private val quote: String,
        private val author: String
    ) : QuotePreview {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.map(quote, author)
    }
}
