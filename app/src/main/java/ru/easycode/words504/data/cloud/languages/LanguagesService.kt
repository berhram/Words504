package ru.easycode.words504.data.cloud.languages

import retrofit2.Call
import retrofit2.http.GET

interface LanguagesService {

    @GET("languages")
    suspend fun getLanguages(): Call<List<LanguageCloud.Base>>
}
