package ru.easycode.words504.sl

import android.content.Context
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences

interface CoreModule : ProvideSharedPreferences {

    class Base(
        private val context: Context,
        private val isDebug: Boolean
    ) : CoreModule {

        private val sharedPref = if (isDebug) {
            ProvideSharedPreferences.Debug(context)
        } else {
            ProvideSharedPreferences.Release(context)
        }

        override fun sharedPreferences() = sharedPref.sharedPreferences()
    }
}
