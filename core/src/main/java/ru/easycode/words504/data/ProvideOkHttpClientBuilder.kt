package ru.easycode.words504.data

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface ProvideOkHttpClientBuilder {

    fun httpClientBuilder(): OkHttpClient.Builder

    abstract class Abstract(
        private val provideInterceptor: ProvideInterceptor,
        private val timeOutSeconds: Long = 60L
    ): ProvideOkHttpClientBuilder {

        private val timeUnit: TimeUnit = TimeUnit.SECONDS

        override fun httpClientBuilder() = OkHttpClient.Builder()
            .addInterceptor(provideInterceptor.interceptor())
            .connectTimeout(timeOutSeconds, timeUnit)
            .readTimeout(timeOutSeconds, timeUnit)
    }

    class Base(
        provideInterceptor: ProvideInterceptor
    ): Abstract(provideInterceptor)
}