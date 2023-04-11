package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface LessonQuoteCloud {

    interface Mapper<T> {
        fun map(value: List<SentenceCloud>, author: String): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("value")
        private val value: List<SentenceCloud>,
        @SerializedName("author")
        private val author: String
    ) : LessonQuoteCloud {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(value, author)
    }
}
