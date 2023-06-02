package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName
import ru.easycode.words504.data.Empty

interface LessonQuoteCloud : Empty {

    interface Mapper<T : Any> {
        fun map(value: List<SentenceCloud>, author: String): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("value")
        private val value: List<SentenceCloud.Base>,
        @SerializedName("author")
        private val author: String
    ) : LessonQuoteCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(value, author)

        override fun isEmpty(): Boolean = value.isEmpty()
    }
}
