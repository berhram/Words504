package ru.easycode.words504.data

import retrofit2.http.GET
import retrofit2.http.Query

interface LanguagesService {

    @GET("languages?type=target HTTP/2")
    suspend fun getLanguages(
        @Query("language") language: String,
        @Query("name") name: String,
        @Query("supports_formality") supports_formality: Boolean
    ): LanguagesCloud
}
