package ru.easycode.words504.core.domain

import retrofit2.Response
import ru.easycode.words504.core.data.HandleError

class HandleHttpError : HandleError<Response<*>, DomainError> {

    override fun handle(errorSource: Response<*>): DomainError =
        when (errorSource.code()) {
            TOO_MANY_REQUESTS_CODE -> TooManyRequestsError(errorSource)
            TRANSLATION_LIMIT_EXCEEDED_CODE -> TranslationLimitExceededError(errorSource)
            in SERVICE_ERRORS_CODES_RANGE -> ServiceTemporaryError(errorSource)
            else -> UnknownHttpError(errorSource)
        }

    companion object {
        private const val TOO_MANY_REQUESTS_CODE = 429
        private const val TRANSLATION_LIMIT_EXCEEDED_CODE = 456
        private val SERVICE_ERRORS_CODES_RANGE = 500..600
    }
}
