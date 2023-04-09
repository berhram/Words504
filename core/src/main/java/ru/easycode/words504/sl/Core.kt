package ru.easycode.words504.sl

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import okhttp3.Interceptor
import retrofit2.Retrofit
import ru.easycode.words504.core.BuildConfig
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cache.storage.Storage
import ru.easycode.words504.data.cloud.*
import ru.easycode.words504.presentation.Communication

interface CoreModule : SharedPrefs,Storage {

    class Core(private val context: Context, idDebug: Boolean) : CoreModule {

        override fun sharedPreferences(key: String): SharedPreferences =
            context.getSharedPreferences(key,Context.MODE_PRIVATE)

        override fun read(key: String, default: String): String {
            TODO("Not yet implemented")
        }

        override fun <T : Any> read(key: String, default: T): T {
            TODO("Not yet implemented")
        }

        override fun save(key: String, value: String) {
            TODO("Not yet implemented")
        }

        override fun save(key: String, obj: Any) {
            TODO("Not yet implemented")
        }
    }
}

interface SharedPrefs {

    fun sharedPreferences(key: String): SharedPreferences
}