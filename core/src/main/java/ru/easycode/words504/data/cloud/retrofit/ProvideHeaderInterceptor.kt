package ru.easycode.words504.data.cloud.retrofit

interface ProvideHeaderInterceptor : ProvideInterceptor {

    abstract class Abstract constructor(
        private val headerName: String,
        private val headerValue: String
    ) : ProvideHeaderInterceptor {

        override fun interceptor(): HeaderInterceptor {
            return HeaderInterceptor.Base(headerName, headerValue)
        }
    }
}
