package ru.easycode.words504.data

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface ProvideOkHttpClientBuilder {

    fun httpClientBuilder(): OkHttpClient.Builder

    abstract class Abstract(
        private val provideInterceptor: ProvideInterceptor,
        private val timeOutProperty: TimeUnit = TimeUnit.SECONDS,
        private val timeOutSeconds: Long = 60L
    ): ProvideOkHttpClientBuilder {

        override fun httpClientBuilder() = OkHttpClient.Builder()
            .addInterceptor(provideInterceptor.interceptor())
            .connectTimeout(timeOutSeconds, timeOutProperty)
            .readTimeout(timeOutSeconds, timeOutProperty)
    }

    class Base(
        provideInterceptor: ProvideInterceptor
    ): Abstract(provideInterceptor)
}