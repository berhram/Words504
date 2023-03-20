package ru.easycode.words504.core.domain

import java.net.ConnectException
import java.net.UnknownHostException
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class HandleDomainErrorTest {

    private lateinit var domainErrorHandler: HandleDomainError
    private lateinit var exceptionThrown: Exception

    @Before
    fun setup() {
        domainErrorHandler = HandleDomainError(
            httpError = HandleHttpError()
        )
    }

    @Test(expected = NoInternetConnectionError::class)
    fun test_receiving_no_internet_connection_exception() {
        exceptionThrown = UnknownHostException()
        domainErrorHandler.handle(exceptionThrown)
    }

    @Test(expected = RefusedConnectionError::class)
    fun test_receiving_refused_connection_exception() {
        exceptionThrown = ConnectException()
        domainErrorHandler.handle(exceptionThrown)
    }

    @Test(expected = ServiceUnavailableError::class)
    fun test_receiving_service_unavailable_exception() {
        exceptionThrown = Exception()
        domainErrorHandler.handle(exceptionThrown)
    }

    @Test(expected = TooManyRequestsError::class)
    fun test_receiving_too_many_requests_exception() {
        val response = Response.error<Int>(429, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        domainErrorHandler.handle(exceptionThrown)
    }

    @Test(expected = TranslationLimitExceededError::class)
    fun test_receiving_limit_exceeded_exception() {
        val response = Response.error<Int>(456, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        domainErrorHandler.handle(exceptionThrown)
    }

    @Test(expected = ServiceTemporaryError::class)
    fun test_receiving_service_temporary_exception() {
        val response = Response.error<Int>(500, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        domainErrorHandler.handle(exceptionThrown)
    }

    @Test(expected = UnknownHttpError::class)
    fun test_receiving_unknown_http_exception() {
        val response = Response.error<Int>(800, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        domainErrorHandler.handle(exceptionThrown)
    }
}
