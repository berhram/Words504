package ru.easycode.words504.translate.data.cloud

import ru.easycode.words504.data.cloud.AbstractCloudDataSource
import ru.easycode.words504.domain.HandleError

interface TranslateCloudDataSource {

    suspend fun translateText(targetLang: String, text: String): TranslateCloud.Base

    class Base(
        private val service: TranslateService,
        errorHandler: HandleError<java.lang.Exception, Throwable>
    ) : AbstractCloudDataSource(errorHandler), TranslateCloudDataSource {
        override suspend fun translateText(targetLang: String, text: String): TranslateCloud.Base =
            handle {
                service.translate(targetLang, text)
            }
    }
}
