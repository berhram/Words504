package ru.easycode.words504.data

interface SimpleStorage : StringStorage {
    class Base(private val provideSharedPref: ProvideSharedPreferences) : SimpleStorage {
        override fun read(key: String, default: String): String {
            val outputString = provideSharedPref.sharedPreferences().getString(key, default)
            return outputString ?: ""
        }

        override fun save(key: String, value: String) {
            provideSharedPref.sharedPreferences().edit().putString(key, value).apply()
        }
    }

}