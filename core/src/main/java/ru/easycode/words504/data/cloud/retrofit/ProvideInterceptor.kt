package ru.easycode.words504.data.cloud.retrofit

import okhttp3.Interceptor

interface ProvideInterceptor {

    fun interceptor(): Interceptor

}
