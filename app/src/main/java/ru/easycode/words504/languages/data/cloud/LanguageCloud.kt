package ru.easycode.words504.languages.data.cloud

import com.google.gson.annotations.SerializedName

interface LanguageCloud {

    fun isLanguageEnglish(): Boolean

    interface Mapper<T> {
        fun map(languageCode: String, name: String): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("language")
        private val languageCode: String,
        @SerializedName("name")
        private val name: String
    ) : LanguageCloud {

        override fun isLanguageEnglish(): Boolean {
            return languageCode.lowercase().startsWith("en")
        }

        override fun <T> map(mapper: Mapper<T>): T = mapper.map(languageCode, name)

    }
}
