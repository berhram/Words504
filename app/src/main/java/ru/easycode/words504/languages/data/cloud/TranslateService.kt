package ru.easycode.words504.languages.data.cloud

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface TranslateService {

    @POST("translate")
    fun translate(
        @Query("target_lang") targetLang: String,
        @Query("text") text: String,
    ): Call<TranslateCloud.Base>
}