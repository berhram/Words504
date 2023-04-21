package ru.easycode.words504.loading

import ru.easycode.words504.loading.adapter.ItemTranslateUi
import ru.easycode.words504.translate.data.cloud.TranslateCloud
import ru.easycode.words504.translate.data.cloud.TranslationsCloud

class TranslateMapper(private val wordToTranslate: String) :
    TranslationsCloud.Mapper<String> {
    override fun map(text: String): String = "$wordToTranslate : $text"
}

class TranslateCloudToItemUiMapper(
    private val id: Int,
    private val wordToTranslate: String
) : TranslateCloud.Mapper<ItemTranslateUi> {
    override fun map(translations: List<TranslationsCloud>): ItemTranslateUi = translations.map {
        ItemTranslateUi.Base(id, it.map(TranslateMapper(wordToTranslate)))
    }.first()
}
