package ru.easycode.words504.data.cloud.retrofit

import okhttp3.Interceptor

interface HeaderInterceptor : Interceptor {

    abstract class Abstract constructor(
        private val headerName: String,
        private val headerValue: String
    ) : HeaderInterceptor {

        override fun intercept(chain: Interceptor.Chain) = chain.run {
            proceed(
                chain.request().newBuilder()
                    .addHeader(headerName, headerValue)
                    .build()
            )
        }
    }

    class Base(headerName: String, headerValue: String) : Abstract(headerName, headerValue)
}
