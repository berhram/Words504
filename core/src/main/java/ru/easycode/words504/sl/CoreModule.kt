package ru.easycode.words504.sl

import android.content.Context
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.presentation.DispatchersList

interface CoreModule : ProvideSharedPreferences, ProvideAdminScopeModule {

    fun provideDispatchers(): DispatchersList

    class Base(
        context: Context,
        isDebug: Boolean
    ) : CoreModule {

        private val sharedPref = if (isDebug) {
            ProvideSharedPreferences.Debug(context)
        } else {
            ProvideSharedPreferences.Release(context)
        }

        private val dispatchers: DispatchersList by lazy { DispatchersList.Base() }

        private val adminScopeModule = AdminScopeModule.Base()
        override fun provideDispatchers(): DispatchersList = dispatchers

        override fun sharedPreferences() = sharedPref.sharedPreferences()

        override fun provideAdminScope() = adminScopeModule
    }
}
