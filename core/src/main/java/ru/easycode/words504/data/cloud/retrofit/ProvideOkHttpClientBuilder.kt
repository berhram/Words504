package ru.easycode.words504.data.cloud.retrofit

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface ProvideOkHttpClientBuilder {

    fun httpClientBuilder(): OkHttpClient.Builder

    abstract class Abstract(
        private val timeOutUnit: TimeUnit = TimeUnit.SECONDS,
        private val timeOut: Long = 60L
    ) : ProvideOkHttpClientBuilder {

        override fun httpClientBuilder() = OkHttpClient.Builder()
            .connectTimeout(timeOut, timeOutUnit)
            .readTimeout(timeOut, timeOutUnit)
    }

    abstract class SingleInterceptor constructor(
        private val provideInterceptor: ProvideInterceptor
    ) : Abstract() {
        override fun httpClientBuilder() = super.httpClientBuilder()
            .addInterceptor(provideInterceptor.interceptor())
    }

    abstract class MultipleInterceptors(
        private vararg val interceptorProviders: ProvideInterceptor
    ) : Abstract() {
        override fun httpClientBuilder(): OkHttpClient.Builder {
            val builder = super.httpClientBuilder()
            interceptorProviders.forEach { provider ->
                builder.addInterceptor(provider.interceptor())
            }
            return builder
        }
    }

    class Base(
        vararg provideInterceptor: ProvideInterceptor
    ) : MultipleInterceptors(*provideInterceptor)
}
