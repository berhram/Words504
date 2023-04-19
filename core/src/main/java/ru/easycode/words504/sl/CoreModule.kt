package ru.easycode.words504.sl

import android.content.Context
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cloud.ProvideConverterFactory
import ru.easycode.words504.data.cloud.ProvideLoggingInterceptor
import ru.easycode.words504.data.cloud.ProvideOkHttpClientBuilder
import ru.easycode.words504.data.cloud.ProvideRetrofitBuilder
import ru.easycode.words504.presentation.DispatchersList

interface CoreModule : ProvideSharedPreferences, ProvideAdminScopeModule, ProvideRetrofit {

    fun provideDispatchers(): DispatchersList

    class Base(
        context: Context,
        isDebug: Boolean
    ) : CoreModule {

        private val sharedPref = if (isDebug) {
            ProvideSharedPreferences.Debug(context)
        } else {
            ProvideSharedPreferences.Release(context)
        }

        private val provideRetrofit: ProvideRetrofitBuilder by lazy {
            ProvideRetrofitBuilder.Base(
                httpClientBuilder = ProvideOkHttpClientBuilder.AddInterceptor(
                    ProvideLoggingInterceptor.Debug(),
                    ProvideOkHttpClientBuilder.AddInterceptor(
                        AuthHeaderInterceptorProvider(),
                        ProvideOkHttpClientBuilder.Base()
                    )
                ),
                provideConverterFactory = ProvideConverterFactory.Base()
            )
        }

        private val dispatchers: DispatchersList = DispatchersList.Base()

        private val adminScopeModule = AdminScopeModule.Base()
        override fun provideDispatchers(): DispatchersList = dispatchers

        override fun sharedPreferences() = sharedPref.sharedPreferences()

        override fun provideAdminScope() = adminScopeModule
        override fun provideRetrofit(): ProvideRetrofitBuilder = provideRetrofit
    }
}
