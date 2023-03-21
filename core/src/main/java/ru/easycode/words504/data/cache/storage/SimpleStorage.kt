package ru.easycode.words504.data.cache.storage

import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences

interface SimpleStorage : StringStorage {
    class Base(provideSharedPref: ProvideSharedPreferences) : SimpleStorage {

        private val sharedPreferences = provideSharedPref.sharedPreferences()
        override fun read(key: String, default: String) =
            sharedPreferences.getString(key, default) ?: default

        override fun save(key: String, value: String) {
            sharedPreferences.edit().putString(key, value).apply()
        }
    }
}
