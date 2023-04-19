package ru.easycode.words504.sl

import ru.easycode.words504.data.cloud.ProvideOkHttpClientBuilder

interface ProvideHttpClientBuilder {
    fun provideHttpClientBuilder() : ProvideOkHttpClientBuilder
}
