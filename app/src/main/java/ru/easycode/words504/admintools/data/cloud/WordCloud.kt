package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface WordCloud {

    interface Mapper<T : Any> {
        fun map(
            id: String,
            index: Int,
            dictionaryForm: String
        ): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: String,
        @SerializedName("index")
        private val index: Int,
        @SerializedName("dictionaryForm")
        private val dictionaryForm: String
    ) : WordCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(id, index, dictionaryForm)
    }
}
