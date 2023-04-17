package ru.easycode.words504.translate.data.cloud

import java.net.UnknownHostException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import ru.easycode.words504.BaseTest
import ru.easycode.words504.domain.HandleError
import ru.easycode.words504.languages.data.cache.ChosenLanguageCache

class TranslateCloudDataSourceTest : BaseTest() {

    //    private val cacheDataSource = LanguagesCacheDataSource.Base(FakeObjectStorage())
    private val objectStorage = FakeObjectStorage()
    private lateinit var cloudDataSource: TranslateCloudDataSource
    private lateinit var service: FakeTranslateService
    private lateinit var chosenLanguageCache: ChosenLanguageCache.Read

    @Before
    fun setUp() {
        chosenLanguageCache = ChosenLanguageCache.Base(objectStorage)
        service = FakeTranslateService()
        val errorHandler: HandleError<Exception, Throwable> = FakeHandleError()
        cloudDataSource = TranslateCloudDataSource.Base(chosenLanguageCache, service, errorHandler)
    }

    @Test
    fun `test empty data success`() = runBlocking {
        val translations = mutableListOf<TranslationsCloud.Base>()

        val actual = cloudDataSource.translateText("")
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

        val actual = cloudDataSource.translateText("apple")
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
