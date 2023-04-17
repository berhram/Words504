package ru.easycode.words504.sl

import android.content.Context
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cloud.MakeService
import ru.easycode.words504.presentation.DispatchersList

interface CoreModule : ProvideSharedPreferences {

    fun provideDispatchers() : DispatchersList
    class Base(
        context: Context,
        isDebug: Boolean
    ) : CoreModule {

        private val sharedPref = if (isDebug) {
            ProvideSharedPreferences.Debug(context)
        } else {
            ProvideSharedPreferences.Release(context)
        }

        private val dispatchersList by lazy {
            DispatchersList.Base()
        }

        override fun provideDispatchers(): DispatchersList = dispatchersList

        override fun sharedPreferences() = sharedPref.sharedPreferences()
    }
}
