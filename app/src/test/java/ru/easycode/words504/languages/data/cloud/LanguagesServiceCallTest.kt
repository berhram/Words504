package ru.easycode.words504.languages.data.cloud

import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import ru.easycode.words504.data.cloud.ProvideConverterFactory
import ru.easycode.words504.data.cloud.ProvideLoggingInterceptor
import ru.easycode.words504.data.cloud.ProvideOkHttpClientBuilder
import ru.easycode.words504.data.cloud.ProvideRetrofitBuilder

// TODO: Remove before the release
class LanguagesServiceCallTest {

    @Test
    @Ignore("Call manually from Android Studio")
    fun `test api call to get list of languages`(): Unit = runBlocking {
        val converterFactory = ProvideConverterFactory.Base()

        val authHeaderInterceptorProvider = AuthHeaderInterceptorProvider()
        val loggingProvider = ProvideLoggingInterceptor.Debug()
        val baseClientBuilder = ProvideOkHttpClientBuilder.AddInterceptor(
            loggingProvider,
            ProvideOkHttpClientBuilder.Base()
        )

        val clientBuilder = ProvideOkHttpClientBuilder.AddInterceptor(
            authHeaderInterceptorProvider,
            baseClientBuilder
        )
        val retrofitBuilder = ProvideRetrofitBuilder.Base(converterFactory, clientBuilder)
        val service = LanguagesMakeService(retrofitBuilder).service(LanguagesService::class.java)
        val result = service.getLanguages().execute()
        println(result)
    }
}