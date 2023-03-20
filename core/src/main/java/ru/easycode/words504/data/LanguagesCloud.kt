package ru.easycode.words504.data

import com.google.gson.annotations.SerializedName

interface LanguagesCloud {

    data class Base(
        @SerializedName("type")
        private val typeLanguages: String
    ) : LanguagesCloud
}
