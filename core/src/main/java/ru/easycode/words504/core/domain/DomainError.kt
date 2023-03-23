package ru.easycode.words504.core.domain

import retrofit2.HttpException
import retrofit2.Response

abstract class DomainError : Throwable()

data class ServiceUnavailableError(override val message: String) : DomainError()

data class NoInternetConnectionError(override val message: String) : DomainError()

data class RefusedConnectionError(override val message: String) : DomainError()

data class TooManyRequestsError(private val response: Response<*>) : HttpException(response)

data class TranslationLimitExceededError(private val response: Response<*>) :
    HttpException(response)

data class UnknownHttpError(private val response: Response<*>) : HttpException(response)

data class ServiceTemporaryError(private val response: Response<*>) : HttpException(response)
