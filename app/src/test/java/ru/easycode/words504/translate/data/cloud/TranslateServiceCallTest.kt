package ru.easycode.words504.translate.data.cloud

import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import ru.easycode.words504.data.cloud.ProvideConverterFactory
import ru.easycode.words504.data.cloud.ProvideLoggingInterceptor
import ru.easycode.words504.data.cloud.ProvideOkHttpClientBuilder
import ru.easycode.words504.data.cloud.ProvideRetrofitBuilder
import ru.easycode.words504.languages.data.cloud.AuthHeaderInterceptorProvider
import ru.easycode.words504.languages.data.cloud.LanguagesMakeService

// TODO: Remove before the release
class TranslateServiceCallTest {

    @Test
    @Ignore("Call manually from Android Studio")
    fun `test api call to try to translate`(): Unit = runBlocking {
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
        val service =
            LanguagesMakeService(retrofitBuilder).service(TranslateService::class.java)
        val result = service.translate("ru", "hello").execute().body()
        println(result)
    }
}
