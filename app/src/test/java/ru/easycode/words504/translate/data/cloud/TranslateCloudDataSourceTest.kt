package ru.easycode.words504.translate.data.cloud

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import ru.easycode.words504.BaseTest
import ru.easycode.words504.domain.HandleError
import java.net.UnknownHostException

class TranslateCloudDataSourceTest : BaseTest() {

    private lateinit var cloudDataSource: TranslateCloudDataSource
    private lateinit var service: FakeTranslateService

    @Before
    fun setUp() {
        service = FakeTranslateService()
        val errorHandler: HandleError<Exception, Throwable> = FakeHandleError()
        cloudDataSource = TranslateCloudDataSource.Base(service, errorHandler)
    }

    @Test
    fun `test empty data success`() = runBlocking {
        val translations = mutableListOf<TranslationsCloud.Base>()

        val actual = cloudDataSource.translateText("", "")
        val expected = TranslateCloud.Base(translations)

        assertEquals(expected, actual)
    }

    @Test
    fun `test translate data`() = runBlocking {
        service.expectedData(
            listOf(
                TranslationsCloud.Base("яблоко")
            )
        )

        val actual = cloudDataSource.translateText("ru", "apple")
        val expected = TranslateCloud.Base(listOf(TranslationsCloud.Base("яблоко")))
        assertEquals(expected, actual)
    }


    private class FakeHandleError : HandleError<Exception, Throwable> {

        override fun handle(source: Exception): Throwable = source
    }

    private class FakeTranslateService : TranslateService {

        private var error: Boolean = false
        private val translations = mutableListOf<TranslationsCloud.Base>()
        private val data = TranslateCloud.Base(translations)


        private val translateCall = object : FakeCall<TranslateCloud.Base>() {
            override fun execute(): Response<TranslateCloud.Base> =
                if (error) throw UnknownHostException() else Response.success(data)
        }

        fun expectedData(newData: List<TranslationsCloud.Base>) {
            with(translations) {
                clear()
                addAll(newData)
            }
        }

        override fun translate(
            targetLang: String,
            text: String,
            sourceLang: String
        ): Call<TranslateCloud.Base> = translateCall
    }
}
