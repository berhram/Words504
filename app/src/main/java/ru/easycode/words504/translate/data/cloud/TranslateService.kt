package ru.easycode.words504.translate.data.cloud

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface TranslateService {

    @POST("translate")
    fun translate(
        @Query("target_lang") targetLang: String,
        @Query("text") text: String,
        @Query("source_lang") sourceLang: String = "EN"
    ): Call<TranslateCloud.Base>
}
