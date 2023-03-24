package ru.easycode.words504.data.cloud

interface MakeService {

    fun <T : Any> service(clazz: Class<T>): T

    abstract class Abstract(
        private val retrofitBuilder: ProvideRetrofitBuilder,
        private val baseUrl: String,
    ) : MakeService {

        private val retrofit by lazy {
            retrofitBuilder.provideRetrofitBuilder()
                .baseUrl(baseUrl)
                .build()
        }

        override fun <T : Any> service(clazz: Class<T>): T = retrofit.create(clazz)
    }
}
