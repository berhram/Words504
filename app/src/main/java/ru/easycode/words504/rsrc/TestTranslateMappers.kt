package ru.easycode.words504.rsrc

import ru.easycode.words504.languages.data.cloud.TranslateCloud
import ru.easycode.words504.languages.data.cloud.TranslationsCloud

class TranslateMapper(private val wordToTranslate: String) :
    TranslationsCloud.Mapper<String> {
    override fun map(text: String): String =
        "$wordToTranslate : $text"
}

class TranslationsMapper(private val wordToTranslate: String) :
    TranslateCloud.Mapper<String> {
    override fun map(translations: List<TranslationsCloud>): String {
        return translations.map { cloudModel ->
            cloudModel.map(TranslateMapper(wordToTranslate))
        }.joinToString()
    }
}
