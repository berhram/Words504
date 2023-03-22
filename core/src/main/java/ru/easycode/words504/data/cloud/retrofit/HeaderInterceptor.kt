package ru.easycode.words504.data.cloud.retrofit

import okhttp3.Interceptor

class HeaderInterceptor(
    private val headerName: String,
    private val headerValue: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            chain.request().newBuilder()
                .addHeader(headerName, headerValue)
                .build()
        )
    }
}
