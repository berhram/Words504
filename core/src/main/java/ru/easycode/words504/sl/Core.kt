package ru.easycode.words504.sl

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import okhttp3.Interceptor
import retrofit2.Retrofit
import ru.easycode.words504.core.BuildConfig
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cloud.*
import ru.easycode.words504.presentation.Communication

interface CoreModule : ProvideRetrofitBuilder, ProvideSharedPreferences {

    class Core(private val context: Context) : CoreModule {

        private val retrofitBuilder = ProvideRetrofitBuilder.Base(
            ProvideConverterFactory.Base(),
            ProvideOkHttpClientBuilder.Base(
                if (BuildConfig.DEBUG)
                    ProvideInterceptor.Debug()
                else
                    ProvideInterceptor.Release()
            )
        )

        override fun provideRetrofitBuilder(): Retrofit.Builder {
            TODO("Not yet implemented")
        }

        override fun sharedPreferences(): SharedPreferences {
            TODO("Not yet implemented")
        }
    }
}