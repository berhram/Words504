package ru.easycode.words504.sl

import android.content.Context
import ru.easycode.words504.admintools.sl.AdminScopeModule
import ru.easycode.words504.admintools.sl.ProvideAdminScopeModule
import ru.easycode.words504.data.cache.preferences.ProvideSharedPreferences
import ru.easycode.words504.data.cache.serialization.Serialization
import ru.easycode.words504.data.cache.storage.ObjectStorage
import ru.easycode.words504.data.cache.storage.SimpleStorage
import ru.easycode.words504.data.cloud.ProvideLoggingInterceptor
import ru.easycode.words504.data.cloud.ProvideOkHttpClientBuilder
import ru.easycode.words504.presentation.DispatchersList
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.presentation.NavigationCommunication

interface CoreModule :
    ProvideSharedPreferences,
    ProvideAdminScopeModule,
    ProvideHttpClientBuilder,
    ProvideNavigation,
    ProvideObjectStorage,
    ManageResources {

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

        private val provideHttpClientBuilder by lazy {
            ProvideOkHttpClientBuilder
                .AddInterceptor(
                    ProvideLoggingInterceptor.Debug(),
                    ProvideOkHttpClientBuilder.Base()
                )
        }

        private val dispatchers: DispatchersList = DispatchersList.Base()
        private val manageResources: ManageResources = ManageResources.Base(context)

        private val adminScopeModule = AdminScopeModule.Base(isDebug, context)
        private val navigation = NavigationCommunication.Base()

        private val objectStorage = ObjectStorage.Base(
            Serialization.Base(),
            SimpleStorage.Base(this)
        )

        override fun provideDispatchers(): DispatchersList = dispatchers

        override fun sharedPreferences() = sharedPref.sharedPreferences()

        override fun string(id: Int): String = manageResources.string(id)

        override fun provideAdminScope() = adminScopeModule

        override fun provideHttpClientBuilder(): ProvideOkHttpClientBuilder =
            provideHttpClientBuilder

        override fun provideNavigation() = navigation

        override fun provideObjectStorage() = objectStorage
    }
}
