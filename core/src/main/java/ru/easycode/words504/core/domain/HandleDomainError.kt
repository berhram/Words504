package ru.easycode.words504.core.domain

import retrofit2.HttpException
import retrofit2.Response
import ru.easycode.words504.core.data.HandleError
import java.net.ConnectException
import java.net.UnknownHostException

class HandleDomainError(
    private val httpError: HandleError<Response<*>, Throwable>
) : HandleError<Exception, Throwable> {

    override fun handle(source: Exception): Throwable =
        when (source) {
            is UnknownHostException -> NoInternetConnectionError(NO_INTERNET_MESSAGE)
            is ConnectException -> RefusedConnectionError(CONNECTION_REFUSED_MESSAGE)
            is HttpException -> httpError.handle(source.response()!!)
            else -> ServiceUnavailableError(SERVICE_UNAVAILABLE_MESSAGE)
        }

    companion object {
        private const val NO_INTERNET_MESSAGE = "No internet connection"
        private const val CONNECTION_REFUSED_MESSAGE = "Connection was refused"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service is unavailable"
    }
}
