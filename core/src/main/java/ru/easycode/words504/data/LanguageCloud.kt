package ru.easycode.words504.data

import com.google.gson.annotations.SerializedName

interface LanguageCloud {

    fun isLanguageEnglish(): Boolean

    data class Base(
        @SerializedName("language")
        private val languageCode: String,
        @SerializedName("name")
        private val name: String,
        @SerializedName("supports_formality")
        private val supportsFormality: Boolean
    ) : LanguageCloud {

        override fun isLanguageEnglish(): Boolean {
            return languageCode == "EN-GB" || languageCode == "EN-US"
        }
    }
}
