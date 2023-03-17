package ru.easycode.words504.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ProvideRetrofitBuilder {

    fun provideRetrofitBuilder(): Retrofit.Builder

    abstract class Base (
        private val provideConverterFactory: ProvideConverterFactory,
        private val httpClientBuilder: ProvideOkHttpClientBuilder
    ) : ProvideRetrofitBuilder {
        override fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
            .client(httpClientBuilder.httpClientBuilder().build())
            .baseUrl(baseUrl)
            .addConverterFactory(provideConverterFactory.converterFactory())

        private val baseUrl: String = "testUrl"
    }
}