package ru.easycode.words504.data

import com.google.gson.annotations.SerializedName

interface LanguagesCloud {

    data class Base(
        @SerializedName("language")
        private val language: String,
        @SerializedName("name")
        private val name: String,
        @SerializedName("supports_formality")
        private val supports_formality: Boolean,
    ) : LanguagesCloud {

        fun isLanguageEnglish(): Boolean {
            return language == "English"
        }
    }
}
