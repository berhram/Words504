package ru.easycode.words504.core.domain

import retrofit2.HttpException
import retrofit2.Response
import ru.easycode.words504.core.data.HandleError
import java.net.UnknownHostException

class HandleDomainError(
    private val httpError: HandleError<Response<*>, DomainError>
) : HandleError<Exception, DomainError> {

    override fun handle(errorSource: Exception): DomainError =
        when (errorSource) {
            is UnknownHostException -> NoInternetConnectionError()
            is HttpException -> httpError.handle(errorSource.response()!!)
            else -> ServiceUnavailableError()
        }
}
