package ru.easycode.words504.data

interface SimpleStorage : StringStorage {

    class Storage(private val provideSharedPref: ProvideSharedPreferences)

}