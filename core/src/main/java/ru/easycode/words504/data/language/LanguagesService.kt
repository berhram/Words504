package ru.easycode.words504.data.language

import retrofit2.http.GET
import retrofit2.http.Query

interface LanguagesService {

    @GET("languages")
    suspend fun getLanguages(
        @Query("type") type: String
    ): LanguageCloud
}
