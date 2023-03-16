package ru.easycode.words504.data

import android.content.Context
import android.content.SharedPreferences

interface ProvideSharedPreferences {

    fun sharedPreferences(): SharedPreferences

    abstract class SharedPref(
        val context: Context,
        val name: String,
        val visibility: Int = Context.MODE_PRIVATE,
    )

    class Release(context: Context) : SharedPref(
        context,
        "release"
    )

    class Debug(context: Context) : SharedPref(
        context,
        "debug"
    )
}