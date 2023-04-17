package ru.easycode.words504.translate.data.cloud

import ru.easycode.words504.data.cloud.AbstractCloudDataSource
import ru.easycode.words504.domain.HandleError
import ru.easycode.words504.languages.data.cache.ChosenLanguageCache

interface TranslateCloudDataSource {

    suspend fun translateText(text: String): TranslateCloud

    class Base(
        private val chosenLanguage: ChosenLanguageCache.Read,
        private val service: TranslateService,
        errorHandler: HandleError<java.lang.Exception, Throwable>
    ) : AbstractCloudDataSource(errorHandler), TranslateCloudDataSource {
        override suspend fun translateText(text: String) = handle {
            val language = chosenLanguage.read()
            language.map(TranslateMapper(service, text))
        }
    }
}
