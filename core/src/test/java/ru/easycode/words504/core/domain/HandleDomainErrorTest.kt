package ru.easycode.words504.core.domain

import java.net.UnknownHostException
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import ru.easycode.words504.core.data.HandleError

class HandleDomainErrorTest {

    private lateinit var domainErrorHandler: HandleDomainError
    private lateinit var exceptionThrown: Exception

    @Before
    fun setup() {
        domainErrorHandler = HandleDomainError(
            httpError = FakeHandleHttp()
        )
    }

    @Test()
    fun test_receiving_no_internet_connection_exception() {
        exceptionThrown = UnknownHostException()
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = NoInternetConnectionError()
        assertEquals(expectedDomainError.javaClass, actual.javaClass)
    }

    @Test
    fun test_receiving_service_unavailable_exception() {
        exceptionThrown = Exception()
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = ServiceUnavailableError()
        assertEquals(expectedDomainError.javaClass, actual.javaClass)
    }

    @Test
    fun test_receiving_too_many_requests_exception() {
        val response = Response.error<Int>(401, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = TooManyRequestsError(response)
        assertEquals(expectedDomainError.javaClass, actual.javaClass)
    }

    @Test
    fun test_receiving_limit_exceeded_exception() {
        val response = Response.error<Int>(402, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = TranslationLimitExceededError(response)
        assertEquals(expectedDomainError.javaClass, actual.javaClass)
    }

    @Test
    fun test_receiving_service_temporary_exception() {
        val response = Response.error<Int>(403, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = ServiceTemporaryError(response)
        assertEquals(expectedDomainError.javaClass, actual.javaClass)
    }

    @Test
    fun test_receiving_unknown_http_exception() {
        val response = Response.error<Int>(700, "errorBody".toResponseBody())
        exceptionThrown = HttpException(response)
        val actual = domainErrorHandler.handle(exceptionThrown)
        val expectedDomainError = UnknownHttpError(response)
        assertEquals(expectedDomainError.javaClass, actual.javaClass)
    }

    private class FakeHandleHttp : HandleError<Response<*>, DomainError> {

        override fun handle(source: Response<*>): DomainError {
            return when (source.code()) {
                401 -> TooManyRequestsError(source)
                402 -> TranslationLimitExceededError(source)
                403 -> ServiceTemporaryError(source)
                else -> UnknownHttpError(source)
            }
        }
    }
}
