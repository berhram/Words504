package ru.easycode.words504.data

interface SimpleStorage : StringStorage {
    class Base(provideSharedPref: ProvideSharedPreferences) : SimpleStorage {

        private val sharedPreferences = provideSharedPref.sharedPreferences()
        override fun read(key: String, default: String): String {
            return sharedPreferences.getString(key, default)!!
        }

        override fun save(key: String, value: String) {
            sharedPreferences.edit().putString(key, value).apply()
        }
    }

}