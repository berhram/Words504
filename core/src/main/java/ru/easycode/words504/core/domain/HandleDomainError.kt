package ru.easycode.words504.core.domain

import java.net.ConnectException
import java.net.UnknownHostException
import retrofit2.HttpException
import retrofit2.Response
import ru.easycode.words504.core.data.HandleError

class HandleDomainError(
    private val httpError: HandleError<Response<*>, DomainError>
) : HandleError<Exception, DomainError> {

    override fun handle(source: Exception): DomainError =
        when (source) {
            is UnknownHostException -> throw NoInternetConnectionError(NO_INTERNET_MESSAGE)
            is ConnectException -> throw RefusedConnectionError(CONNECTION_REFUSED_MESSAGE)
            is HttpException -> httpError.handle(source.response()!!)
            else -> throw ServiceUnavailableError(SERVICE_UNAVAILABLE_MESSAGE)
        }

    companion object {
        private const val NO_INTERNET_MESSAGE = "No internet connection"
        private const val CONNECTION_REFUSED_MESSAGE = "Connection was refused"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service is unavailable"
    }
}
