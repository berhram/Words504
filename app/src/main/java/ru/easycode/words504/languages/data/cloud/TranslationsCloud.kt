package ru.easycode.words504.languages.data.cloud

import com.google.gson.annotations.SerializedName

interface TranslationsCloud {
    interface Mapper<T> {
        fun map(text: String): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("text")
        private val text: String
    ) : TranslationsCloud {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(text)
    }
}
