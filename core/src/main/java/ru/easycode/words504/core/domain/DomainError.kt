package ru.easycode.words504.core.domain

import retrofit2.HttpException
import retrofit2.Response

interface DomainError

data class ServiceUnavailableError(override val message: String) : Exception(), DomainError

data class NoInternetConnectionError(override val message: String) : Exception(), DomainError

data class RefusedConnectionError(override val message: String) : Exception(), DomainError

data class TooManyRequestsError(private val response: Response<*>) :
    HttpException(response), DomainError

data class TranslationLimitExceededError(private val response: Response<*>) :
    HttpException(response), DomainError

data class UnknownHttpError(private val response: Response<*>) :
    HttpException(response), DomainError

data class ServiceTemporaryError(private val response: Response<*>) :
    HttpException(response), DomainError
