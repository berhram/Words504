package ru.easycode.words504.data.cloud.languages

import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import ru.easycode.words504.core.data.HandleError
import ru.easycode.words504.core.domain.HandleHttpError

class LanguagesCloudDataSourceTest {

    private lateinit var cloudDataSource: TestLanguagesCloudDataSource

    @Before
    fun setUp() {
        val errorHandler: HandleError<Response<*>, Throwable> = HandleHttpError()
        cloudDataSource = TestLanguagesCloudDataSource(errorHandler)
    }

    @Test
    fun `test empty data success`() = runBlocking {
        cloudDataSource.expectedData(emptyList())
        cloudDataSource.expectedError(false)

        val actual = cloudDataSource.languages()
        val expected = emptyList<LanguageCloud.Base>()

        assertEquals(expected, actual)
    }

    @Test
    fun `test some data success`() = runBlocking {
        cloudDataSource.expectedData(
            listOf(
                LanguageCloud.Base("ru", "russian"),
                LanguageCloud.Base("ch", "chinese")
            )
        )
        cloudDataSource.expectedError(false)

        val actual = cloudDataSource.languages()
        val expected = listOf(
            LanguageCloud.Base("ru", "russian"),
            LanguageCloud.Base("ch", "chinese")
        )
        assertEquals(expected, actual)
    }

    @Test(expected = Exception::class)
    fun `test error`() = runBlocking {
        cloudDataSource.expectedError(true)

        val actual = cloudDataSource.languages()
        val expected = emptyList<LanguageCloud.Base>()

        assertEquals(expected, actual)
    }

    private class TestLanguagesCloudDataSource(
        private val errorHandler: HandleError<Response<*>, Throwable>
    ) : LanguagesCloudDataSource {

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

        override suspend fun languages(): List<LanguageCloud> {
            if (error) {
                val response: Response<List<LanguageCloud>> = Response.error(
                    404,
                    "Not found".toResponseBody()
                )
                throw errorHandler.handle(response)
            } else {
                val success: Response<List<LanguageCloud>> = Response.success(data)
                return success.body() ?: emptyList()
            }
        }
    }
}
