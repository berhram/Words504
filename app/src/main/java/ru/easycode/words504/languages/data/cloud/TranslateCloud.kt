package ru.easycode.words504.languages.data.cloud

import com.google.gson.annotations.SerializedName

interface TranslateCloud {
    interface Mapper<T> {
        fun map(translations: List<TranslationsCloud>): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("translations")
        private val translations: List<TranslationsCloud.Base>,
    ) : TranslateCloud {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(translations)
    }
}