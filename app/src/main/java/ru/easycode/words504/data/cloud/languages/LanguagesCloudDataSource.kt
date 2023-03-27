package ru.easycode.words504.data.cloud.languages

import retrofit2.Response
import retrofit2.awaitResponse
import ru.easycode.words504.core.data.HandleError

interface LanguagesCloudDataSource {

    suspend fun languages(): List<LanguageCloud>

    class Base(
        private val service: LanguagesService,
        private val errorHandler: HandleError<Response<*>, Throwable>,
    ) : LanguagesCloudDataSource {

        override suspend fun languages(): List<LanguageCloud> {
            val response = service.getLanguages().awaitResponse()
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            } else {
                throw errorHandler.handle(response)
            }
        }
    }
}
