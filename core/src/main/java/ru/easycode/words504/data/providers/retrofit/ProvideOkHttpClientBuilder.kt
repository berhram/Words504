package ru.easycode.words504.data.providers.retrofit

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface ProvideOkHttpClientBuilder {

    fun httpClientBuilder(): OkHttpClient.Builder

    abstract class Abstract(
        private val provideInterceptor: ProvideInterceptor,
        private val timeOutUnit: TimeUnit = TimeUnit.SECONDS,
        private val timeOut: Long = 60L
    ): ProvideOkHttpClientBuilder {

        override fun httpClientBuilder() = OkHttpClient.Builder()
            .addInterceptor(provideInterceptor.interceptor())
            .connectTimeout(timeOut, timeOutUnit)
            .readTimeout(timeOut, timeOutUnit)
    }

    class Base(
        provideInterceptor: ProvideInterceptor
    ): Abstract(provideInterceptor)
}
