package ru.easycode.words504.sl

import android.content.Context
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences

interface CoreModule : ProvideSharedPreferences {

    class Base(
        context: Context,
        isDebug: Boolean
    ) : CoreModule {

        private val sharedPref = if (isDebug) {
            ProvideSharedPreferences.Debug(context)
        } else {
            ProvideSharedPreferences.Release(context)
        }

        override fun sharedPreferences() = sharedPref.sharedPreferences()
    }
}
