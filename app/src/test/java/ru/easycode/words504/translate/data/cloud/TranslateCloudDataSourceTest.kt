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
import ru.easycode.words504.languages.data.cache.LanguageCache

class TranslateCloudDataSourceTest : BaseTest() {

    private lateinit var cloudDataSource: TranslateCloudDataSource

    @Before
    fun setUp() {
        cloudDataSource = TranslateCloudDataSource.Base(
            FakeChosenLanguageCache(),
            FakeTranslateService(),
            FakeHandleError()
        )
    }

    @Test
    fun `test translate data`() = runBlocking {
        val actual = cloudDataSource.translateText("apple")
        val expected = TranslateCloud.Base(listOf(TranslationsCloud.Base("RU" + "apple")))
        assertEquals(expected, actual)
    }

    private class FakeHandleError : HandleError<Exception, Throwable> {

        override fun handle(source: Exception): Throwable = source
    }

    private class FakeTranslateService : TranslateService {

        override fun translate(
            targetLang: String,
            text: String,
            sourceLang: String
        ): Call<TranslateCloud.Base> = Translate(targetLang, text)
    }

    private class Translate(private val targetLang: String, private val text: String) :
        FakeCall<TranslateCloud.Base>() {

        override fun execute(): Response<TranslateCloud.Base> = Response.success(
            TranslateCloud.Base(
                listOf(
                    TranslationsCloud.Base(targetLang + text)
                )
            )
        )
    }
}

private class FakeChosenLanguageCache : ChosenLanguageCache.Read {
    override fun read(): LanguageCache {
        return LanguageCache.Base("RU", "apple")
    }
}
