package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName
import ru.easycode.words504.data.Empty

interface LessonTextCloud : Empty {

    interface Mapper<T : Any> {
        fun map(title: SentenceCloud, sentences: List<SentenceCloud>): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("title")
        private val title: SentenceCloud.Base,
        @SerializedName("sentences")
        private val sentences: List<SentenceCloud.Base>
    ) : LessonTextCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(title, sentences)

        override fun isEmpty(): Boolean = sentences.isEmpty()
    }
}
