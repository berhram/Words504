package ru.easycode.words504.core.data

import ru.easycode.words504.data.ProvideRetrofitBuilder

interface MakeService {

    fun <T> service(clazz: Class<T>): T

    abstract class Abstract(
        private val retrofitBuilder: ProvideRetrofitBuilder
    ) : MakeService {

        private val retrofit by lazy {
            retrofitBuilder.provideRetrofitBuilder()
                .baseUrl(baseUrl())
                .build()
        }

        override fun <T> service(clazz: Class<T>): T = retrofit.create(clazz)

        protected open fun baseUrl(): String = "https://api-free.deepl.com/v2/"
    }
}
