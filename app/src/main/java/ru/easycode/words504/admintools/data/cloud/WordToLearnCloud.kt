package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface WordToLearnCloud {

    interface Mapper<T> {
        fun map(
            id: String,
            definitions: List<SentenceCloud>,
            examples: List<SentenceCloud>
        ): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: String,
        @SerializedName("definitions")
        private val definitions: List<SentenceCloud>,
        @SerializedName("examples")
        private val examples: List<SentenceCloud>
    ) : WordToLearnCloud {

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, definitions, examples)
    }
}
