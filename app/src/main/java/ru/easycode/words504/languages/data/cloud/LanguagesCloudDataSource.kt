package ru.easycode.words504.languages.data.cloud

import ru.easycode.words504.domain.HandleError

interface LanguagesCloudDataSource {

    suspend fun languages(): List<LanguageCloud>

    class Base(
        private val service: LanguagesService,
        private val errorHandler: HandleError<Exception, Throwable>
    ) : LanguagesCloudDataSource {

        override suspend fun languages(): List<LanguageCloud> {
            try {
                val response = service.getLanguages().execute()
                return response.body()!!
            } catch (e: Exception) {
                throw errorHandler.handle(e)
            }
        }
    }
}
