package ru.easycode.words504.translate.data.cloud

import retrofit2.Call
import ru.easycode.words504.languages.data.cache.LanguageCache

class TranslateMapper(
    private val service: TranslateService,
    private val text: String
) : LanguageCache.Mapper<Call<TranslateCloud.Base>> {

    override fun map(key: String, name: String): Call<TranslateCloud.Base> =
        service.translate(key, text)
}
