package ru.easycode.words504.languages.data.cloud

import ru.easycode.words504.data.cloud.AbstractCloudDataSource
import ru.easycode.words504.domain.HandleError

interface LanguagesCloudDataSource {

    suspend fun languages(): List<LanguageCloud>

    class Base(
        private val service: LanguagesService,
        errorHandler: HandleError<Exception, Throwable>
    ) : AbstractCloudDataSource(errorHandler), LanguagesCloudDataSource {

        override suspend fun languages(): List<LanguageCloud> = handle {
            service.getLanguages()
        }
    }
}
