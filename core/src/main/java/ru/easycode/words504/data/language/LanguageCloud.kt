package ru.easycode.words504.data.language

import com.google.gson.annotations.SerializedName

interface LanguageCloud {

    fun isLanguageEnglish(): Boolean

    data class Base(
        @SerializedName("language")
        private val languageCode: String,
        @SerializedName("name")
        private val name: String
    ) : LanguageCloud {

        override fun isLanguageEnglish(): Boolean {
            return languageCode.lowercase().startsWith("en")
        }
    }
}
