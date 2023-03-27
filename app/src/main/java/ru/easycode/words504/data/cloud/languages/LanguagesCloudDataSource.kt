package ru.easycode.words504.data.cloud.languages

interface LanguagesCloudDataSource {

    suspend fun languages(): List<LanguageCloud>

    class Base(private val service: LanguagesService) : LanguagesCloudDataSource {

        override suspend fun languages(): List<LanguageCloud> {
            try {
                val response = service.getLanguages().execute()
                return response.body() ?: throw IllegalStateException("Service unavailable")
            } catch (e: Exception) {
                throw e
            }
        }
    }
}
