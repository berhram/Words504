package ru.easycode.words504.translate.data.cloud

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
        val translationsCloud = TranslationsCloud.Base("")
        val translations = mutableListOf(translationsCloud)

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

        private val translations = mutableListOf<TranslationsCloud.Base>()

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
        ): Call<TranslateCloud.Base> = Translate(targetLang, text)
    }

    private class Translate(private val targetLang: String, private val text: String) :
        FakeCall<TranslateCloud.Base>() {

        override fun execute(): Response<TranslateCloud.Base> {
            return Response.success(TranslateCloud.Base(listOf(TranslationsCloud.Base(targetLang + text))))
        }
    }
}
