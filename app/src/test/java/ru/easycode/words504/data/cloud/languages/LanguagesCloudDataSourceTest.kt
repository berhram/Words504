package ru.easycode.words504.data.cloud.languages

import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Timeout
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LanguagesCloudDataSourceTest {

    private lateinit var cloudDataSource: LanguagesCloudDataSource
    private lateinit var service: TestLanguagesService

    @Before
    fun setUp() {
        service = TestLanguagesService()
        cloudDataSource = LanguagesCloudDataSource.Base(service)
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

    @Test(expected = Exception::class)
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
                        Response.error(
                            404,
                            "Not found".toResponseBody()
                        )
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