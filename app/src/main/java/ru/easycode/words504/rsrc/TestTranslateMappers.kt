package ru.easycode.words504.rsrc

import ru.easycode.words504.translate.data.cloud.TranslateCloud
import ru.easycode.words504.translate.data.cloud.TranslationsCloud

class TranslateMapper(private val wordToTranslate: String) :
    TranslationsCloud.Mapper<String> {
    override fun map(text: String): String =
        "$wordToTranslate : $text"
}

class TranslationsMapper(private val wordToTranslate: String) :
    TranslateCloud.Mapper<String> {
    override fun map(translations: List<TranslationsCloud>): String {
        return translations.joinToString { cloudModel ->
            cloudModel.map(TranslateMapper(wordToTranslate))
        }
    }
}
