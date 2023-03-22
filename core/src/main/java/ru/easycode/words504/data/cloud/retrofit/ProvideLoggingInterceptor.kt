package ru.easycode.words504.data.cloud.retrofit

import okhttp3.logging.HttpLoggingInterceptor

interface ProvideLoggingInterceptor : ProvideInterceptor {

    abstract class Abstract constructor(
        private val loggingLevel: HttpLoggingInterceptor.Level
    ) : ProvideLoggingInterceptor {

        override fun interceptor() = HttpLoggingInterceptor().apply {
            level = loggingLevel
        }
    }

    class Debug : Abstract(HttpLoggingInterceptor.Level.BODY)

    class Release : Abstract(HttpLoggingInterceptor.Level.NONE)
}