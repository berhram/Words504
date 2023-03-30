package ru.easycode.words504.data.cloud

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface ProvideOkHttpClientBuilder {

    fun httpClientBuilder(): OkHttpClient.Builder

    class Base(
        private val timeOutUnit: TimeUnit = TimeUnit.SECONDS,
        private val timeOut: Long = 60L
    ) : ProvideOkHttpClientBuilder {

        override fun httpClientBuilder() = OkHttpClient.Builder()
            .connectTimeout(timeOut, timeOutUnit)
            .readTimeout(timeOut, timeOutUnit)
    }

    class AddInterceptor(
        private val interceptor: ProvideInterceptor,
        private val provideOkHttp: ProvideOkHttpClientBuilder
    ) : ProvideOkHttpClientBuilder {
        override fun httpClientBuilder(): OkHttpClient.Builder {
            return provideOkHttp.httpClientBuilder().addInterceptor(interceptor.interceptor())
        }
    }
}
