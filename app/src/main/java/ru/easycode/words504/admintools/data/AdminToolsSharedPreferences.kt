package ru.easycode.words504.admintools.data

import android.content.Context
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences

class AdminToolsSharedPreferences(context: Context) :
    ProvideSharedPreferences.SharedPref(context, "admin-tools")
