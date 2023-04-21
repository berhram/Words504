package ru.easycode.words504.translate.data.cloud

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import ru.easycode.words504.BaseTest

class TranslateMapperTest : BaseTest() {

    @Test
    fun `test should map to service request`() = runBlocking {
        val service = FakeTranslateService()
        val translateMapper = TranslateMapper(service, "cat")
        val actual = translateMapper.map("en", "english").execute().body()
        val expected = TranslateCloud.Base(listOf(TranslationsCloud.Base("en" + "cat")))
        assertEquals(expected, actual)
    }

    private class FakeTranslateService : TranslateService {
        override fun translate(
            targetLang: String,
            text: String,
            sourceLang: String
        ): Call<TranslateCloud.Base> = Translate(targetLang, text)
    }

    private class Translate(
        private val targetLang: String,
        private val text: String
    ) : FakeCall<TranslateCloud.Base>() {

        override fun execute(): Response<TranslateCloud.Base> = Response.success(
            TranslateCloud.Base(
                listOf(
                    TranslationsCloud.Base(targetLang + text)
                )
            )
        )
    }
}
