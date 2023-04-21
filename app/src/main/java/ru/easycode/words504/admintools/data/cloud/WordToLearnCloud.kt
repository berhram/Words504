package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface WordToLearnCloud {

    interface Mapper<T : Any> {
        fun map(
            id: String,
            definitions: List<SentenceCloud>,
            examples: List<SentenceCloud>
        ): T
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: String,
        @SerializedName("definitions")
        private val definitions: List<SentenceCloud.Base>,
        @SerializedName("examples")
        private val examples: List<SentenceCloud.Base>
    ) : WordToLearnCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(id, definitions, examples)
    }
}
