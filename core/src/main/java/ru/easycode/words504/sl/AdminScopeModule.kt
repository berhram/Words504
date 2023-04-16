package ru.easycode.words504.sl

import ru.easycode.words504.presentation.NavigationCommunication

interface AdminScopeModule: ProvideNavigation {
    class Base : AdminScopeModule {

        private val navigationCommunication = NavigationCommunication.Base()

        override fun provideNavigation() = navigationCommunication
    }
}
