package ru.easycode.words504.data.cache.preferences

import android.content.Context
import android.content.SharedPreferences

interface ProvideSharedPreferences {

    fun sharedPreferences(): SharedPreferences

    abstract class SharedPref(
        private val context: Context,
        private val name: String,
        private val visibility: Int = Context.MODE_PRIVATE,
    ) : ProvideSharedPreferences {
        override fun sharedPreferences(): SharedPreferences =
            context.getSharedPreferences(name, visibility)
    }

    class Release(context: Context) : SharedPref(
        context,
        "release"
    )

    class Debug(context: Context) : SharedPref(
        context,
        "debug"
    )
}
