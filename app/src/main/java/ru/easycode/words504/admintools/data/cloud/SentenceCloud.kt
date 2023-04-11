package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface SentenceCloud {

    interface Mapper<T> {
        fun map(ui: String, words: List<WordCloud>): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("ui")
        private val ui: String,
        @SerializedName("words")
        private val words: List<WordCloud>
    ) : SentenceCloud {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(ui, words)
    }
}
