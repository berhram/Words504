package ru.easycode.words504.admintools.data.cloud

import com.google.gson.annotations.SerializedName

interface WordCloud {

    interface Mapper<T : Any> {
        fun map(
            ui: String,
            index: Int,
            dictionaryForm: String
        ): T

        object Ui : Mapper<String> {
            override fun map(ui: String, index: Int, dictionaryForm: String): String = ui
        }
    }

    fun <T : Any> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("ui")
        private val ui: String,
        @SerializedName("index")
        private val index: Int,
        @SerializedName("dictionaryForm")
        private val dictionaryForm: String
    ) : WordCloud {

        override fun <T : Any> map(mapper: Mapper<T>): T = mapper.map(ui, index, dictionaryForm)
    }
}
