package ru.easycode.words504.data

import okhttp3.logging.HttpLoggingInterceptor

interface ProvideInterceptor {

    fun interceptor(): HttpLoggingInterceptor

    abstract class Abstract(private val loggingLevel: HttpLoggingInterceptor.Level) :
        ProvideInterceptor {

        override fun interceptor() = HttpLoggingInterceptor().apply {
            level = loggingLevel
        }
    }

    class Debug : Abstract(HttpLoggingInterceptor.Level.NONE)

    class Release : Abstract(HttpLoggingInterceptor.Level.BODY)

}