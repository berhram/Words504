package ru.easycode.words504.sl

import android.content.Context
import android.content.SharedPreferences
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cache.storage.Storage

interface CoreModule : ProvideSharedPreferences {

    class Base(
        private val context: Context,
        private val isDebug: Boolean
    ) : CoreModule {

        private val sharedPref = if (isDebug)
            ProvideSharedPreferences.Debug(context)
        else
            ProvideSharedPreferences.Release(context)

        override fun sharedPreferences(): SharedPreferences {
            return sharedPref.sharedPreferences()
        }
    }
}
