package ru.easycode.words504.data.cloud.languages

import java.net.UnknownHostException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import ru.easycode.words504.BaseTest
import ru.easycode.words504.core.data.HandleError

class LanguagesCloudDataSourceTest : BaseTest() {

    private lateinit var cloudDataSource: LanguagesCloudDataSource
    private lateinit var service: FakeLanguagesService

    @Before
    fun setUp() {
        service = FakeLanguagesService()
        val errorHandler: HandleError<Exception, Throwable> = FakeHandleError()
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

    @Test(expected = Exception::class)
    fun `test error`() = runBlocking {
        service.expectedError(true)

        val actual = cloudDataSource.languages()
        val expected = emptyList<LanguageCloud.Base>()

        assertEquals(expected, actual)
    }

    private class FakeHandleError : HandleError<Exception, Throwable> {

        override fun handle(source: Exception): Throwable = source
    }

    private class FakeLanguagesService : LanguagesService {

        private var error: Boolean = false
        private val data = mutableListOf<LanguageCloud.Base>()

        private val languagesCall = object : FakeCall<List<LanguageCloud.Base>>() {
            override fun execute(): Response<List<LanguageCloud.Base>> =
                if (error)
                    throw UnknownHostException()
                else
                    Response.success(data)
        }

        fun expectedError(isError: Boolean) {
            error = isError
        }

        fun expectedData(newData: List<LanguageCloud.Base>) {
            with(data) {
                clear()
                addAll(newData)
            }
        }

        override suspend fun getLanguages(): Call<List<LanguageCloud.Base>> = languagesCall
    }
}
