package ru.easycode.words504.core.domain

import retrofit2.HttpException
import retrofit2.Response

interface DomainError

class ServiceUnavailableError : Exception(), DomainError

class NoInternetConnectionError : Exception(), DomainError

class TooManyRequestsError(response: Response<*>) : HttpException(response), DomainError

class TranslationLimitExceededError(response: Response<*>) : HttpException(response),
    DomainError

class UnknownHttpError(response: Response<*>) : HttpException(response), DomainError

class ServiceTemporaryError(response: Response<*>) : HttpException(response), DomainError
