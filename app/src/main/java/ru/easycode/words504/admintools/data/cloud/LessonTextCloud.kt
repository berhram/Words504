package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface LessonTextCloud {

    interface Mapper<T : Any> {
        fun map(title: SentenceCloud, sentences: List<SentenceCloud>): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("title")
        private val title: SentenceCloud,
        @SerializedName("sentences")
        private val sentences: List<SentenceCloud>
    ) : LessonTextCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(title, sentences)
    }
}
