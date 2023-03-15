package ru.easycode.words504.data

import android.content.Context
import android.content.SharedPreferences

interface SharedPrefWrapper {

    fun create(context: Context):SharedPreferences

     open class SharedPref(
        val name:String,
        val visibility: Int
    )

    class Release: SharedPref("test", Context.MODE_APPEND)
    class Debug: SharedPref("test", Context.MODE_APPEND)
}