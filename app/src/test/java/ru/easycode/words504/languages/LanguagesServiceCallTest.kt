package ru.easycode.words504.languages

import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import ru.easycode.words504.core.data.MakeService
import ru.easycode.words504.data.cloud.AuthHeaderInterceptorProvider
import ru.easycode.words504.data.cloud.languages.LanguagesService
import ru.easycode.words504.data.cloud.retrofit.ProvideConverterFactory
import ru.easycode.words504.data.cloud.retrofit.ProvideLoggingInterceptor
import ru.easycode.words504.data.cloud.retrofit.ProvideOkHttpClientBuilder
import ru.easycode.words504.data.cloud.retrofit.ProvideRetrofitBuilder

//TODO: Remove before the release
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
        val service = MakeService.Dictionary(retrofitBuilder).service(LanguagesService::class.java)
        val result = service.getLanguages().execute()
        println(result)
    }
}
