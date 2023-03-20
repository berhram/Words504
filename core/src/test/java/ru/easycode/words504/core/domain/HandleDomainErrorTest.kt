package ru.easycode.words504.core.domain

import java.net.ConnectException
import java.net.UnknownHostException
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
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

    @Test
    fun test_receiving_no_internet_connection_exception() {
        exceptionThrown = UnknownHostException()
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = NoInternetConnectionError("No internet connection")
        assertEquals(expectedDomainError, actual)
    }

    @Test
    fun test_receiving_refused_connection_exception() {
        exceptionThrown = ConnectException()
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = RefusedConnectionError("Connection was refused")
        assertEquals(expectedDomainError, actual)
    }

    @Test
    fun test_receiving_service_unavailable_exception() {
        exceptionThrown = Exception()
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = ServiceUnavailableError("Service is unavailable")
        assertEquals(expectedDomainError, actual)
    }

    @Test
    fun test_receiving_too_many_requests_exception() {
        val response = Response.error<Int>(429, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = TooManyRequestsError(response)
        assertEquals(expectedDomainError, actual)
    }

    @Test
    fun test_receiving_limit_exceeded_exception() {
        val response = Response.error<Int>(456, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = TranslationLimitExceededError(response)
        assertEquals(expectedDomainError, actual)
    }

    @Test
    fun test_receiving_service_temporary_exception() {
        val response = Response.error<Int>(500, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = ServiceTemporaryError(response)
        assertEquals(expectedDomainError, actual)
    }

    @Test
    fun test_receiving_unknown_http_exception() {
        val response = Response.error<Int>(800, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = UnknownHttpError(response)
        assertEquals(expectedDomainError, actual)
    }
}
