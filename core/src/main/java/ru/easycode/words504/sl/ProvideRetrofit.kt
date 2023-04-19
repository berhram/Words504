package ru.easycode.words504.sl

import ru.easycode.words504.data.cloud.ProvideRetrofitBuilder

interface ProvideRetrofit {
    fun provideRetrofit() : ProvideRetrofitBuilder
}
