package ru.easycode.words504.data

import android.content.Context
import android.content.SharedPreferences

interface ProvideSharedPreferences {

    fun sharedPreferences(): SharedPreferences

    abstract class SharedPref(
        val name: String,
        val visibility: Int,
        val context: Context
    )

    class Release(context: Context) : SharedPref(
        "release",
        Context.MODE_PRIVATE,
        context
    )
    class Debug(context: Context) : SharedPref(
        "debug",
        Context.MODE_PRIVATE,
        context
    )
}