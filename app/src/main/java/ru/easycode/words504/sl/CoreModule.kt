package ru.easycode.words504.sl

import android.content.Context
import ru.easycode.words504.admintools.sl.AdminScopeModule
import ru.easycode.words504.admintools.sl.ProvideAdminScopeModule
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cache.serialization.Serialization
import ru.easycode.words504.data.cache.storage.ObjectStorage
import ru.easycode.words504.data.cache.storage.SimpleStorage
import ru.easycode.words504.presentation.NavigationCommunication

interface CoreModule : ProvideSharedPreferences, ProvideAdminScopeModule, ProvideNavigation,
    ProvideObjectStorage {

    class Base(
        context: Context,
        isDebug: Boolean
    ) : CoreModule {

        private val sharedPref = if (isDebug) {
            ProvideSharedPreferences.Debug(context)
        } else {
            ProvideSharedPreferences.Release(context)
        }

        private val adminScopeModule = AdminScopeModule.Base()

        private val navigation = NavigationCommunication.Base()

        private val objectStorage = ObjectStorage.Base(
            Serialization.Base(),
            SimpleStorage.Base(this)
        )

        override fun sharedPreferences() = sharedPref.sharedPreferences()

        override fun provideAdminScope() = adminScopeModule

        override fun provideNavigation() = navigation

        override fun provideObjectStorage() = objectStorage
    }
}
