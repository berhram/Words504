package ru.easycode.words504.data.cloud.languages

import java.net.UnknownHostException
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okio.Timeout
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.easycode.words504.core.data.HandleError
import ru.easycode.words504.core.domain.HandleDomainError
import ru.easycode.words504.core.domain.HandleHttpError
import ru.easycode.words504.core.domain.NoInternetConnectionError

class LanguagesCloudDataSourceTest {

    private lateinit var cloudDataSource: LanguagesCloudDataSource
    private lateinit var service: TestLanguagesService

    @Before
    fun setUp() {
        service = TestLanguagesService()
        val errorHandler: HandleError<Exception, Throwable> = HandleDomainError(HandleHttpError())
        cloudDataSource = LanguagesCloudDataSource.Base(service, errorHandler)
    }

    @Test
    fun `test empty data success`() = runBlocking {
        service.expectedData(emptyList())
        service.expectedError(false)

        val actual = cloudDataSource.languages()
        val expected = emptyList<LanguageCloud.Base>()

        assertEquals(expected, actual)
    }

    @Test
    fun `test some data success`() = runBlocking {
        service.expectedData(
            listOf(
                LanguageCloud.Base("ru", "russian"),
                LanguageCloud.Base("ch", "chinese")
            )
        )
        service.expectedError(false)

        val actual = cloudDataSource.languages()
        val expected = listOf(
            LanguageCloud.Base("ru", "russian"),
            LanguageCloud.Base("ch", "chinese")
        )
        assertEquals(expected, actual)
    }

    @Test(expected = NoInternetConnectionError::class)
    fun `test error`() = runBlocking {
        service.expectedError(true)

        val actual = cloudDataSource.languages()
        val expected = emptyList<LanguageCloud.Base>()

        assertEquals(expected, actual)
    }

    private class TestLanguagesService : LanguagesService {

        private var error: Boolean = false
        private val data = mutableListOf<LanguageCloud.Base>()

        fun expectedError(isError: Boolean) {
            error = isError
        }

        fun expectedData(newData: List<LanguageCloud.Base>) {
            with(data) {
                clear()
                addAll(newData)
            }
        }

        override suspend fun getLanguages(): Call<List<LanguageCloud.Base>> {
            return object : Call<List<LanguageCloud.Base>> {

                override fun clone(): Call<List<LanguageCloud.Base>> = this

                override fun execute(): Response<List<LanguageCloud.Base>> {
                    return if (error) {
                        throw UnknownHostException()
                    } else {
                        Response.success(data)
                    }
                }

                override fun enqueue(callback: Callback<List<LanguageCloud.Base>>) = Unit

                override fun isExecuted(): Boolean = true

                override fun cancel() = Unit

                override fun isCanceled(): Boolean = true

                override fun request(): Request = Request.Builder().build()

                override fun timeout(): Timeout = Timeout()
            }
        }
    }
}
