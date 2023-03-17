package ru.easycode.words504.data

import retrofit2.Retrofit

interface ProvideRetrofitBuilder {

    fun provideRetrofitBuilder(): Retrofit.Builder

    class Base(
        private val httpClientBuilder: ProvideOkHttpClientBuilder
    ) : ProvideRetrofitBuilder {
        override fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
            .client(httpClientBuilder.httpClientBuilder().build())
    }
}