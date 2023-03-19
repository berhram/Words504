package ru.easycode.words504.core.domain

import java.net.UnknownHostException
import retrofit2.HttpException
import retrofit2.Response
import ru.easycode.words504.core.data.HandleError

class HandleDomainError(
    private val httpError: HandleError<Response<*>, DomainError>
) : HandleError<Exception, DomainError> {

    override fun handle(source: Exception): DomainError =
        when (source) {
            is UnknownHostException -> NoInternetConnectionError()
            is HttpException -> httpError.handle(source.response()!!)
            else -> ServiceUnavailableError()
        }
}
