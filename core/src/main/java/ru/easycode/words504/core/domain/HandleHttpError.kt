package ru.easycode.words504.core.domain

import retrofit2.Response
import ru.easycode.words504.core.data.HandleError

class HandleHttpError : HandleError<Response<*>, Throwable> {

    override fun handle(source: Response<*>): Throwable =
        when (source.code()) {
            TOO_MANY_REQUESTS_CODE ->  TooManyRequestsError(source)
            TRANSLATION_LIMIT_EXCEEDED_CODE ->  TranslationLimitExceededError(source)
            in SERVICE_ERRORS_CODES_RANGE ->  ServiceTemporaryError(source)
            else ->  UnknownHttpError(source)
        }

    companion object {
        private const val TOO_MANY_REQUESTS_CODE = 429
        private const val TRANSLATION_LIMIT_EXCEEDED_CODE = 456
        private val SERVICE_ERRORS_CODES_RANGE = 500..600
    }
}
