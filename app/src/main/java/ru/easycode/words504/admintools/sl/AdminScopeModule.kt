package ru.easycode.words504.admintools.sl

import ru.easycode.words504.admintools.presentation.SentenceUiCache
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.sl.ProvideNavigation

interface AdminScopeModule : ProvideNavigation, ProvideSentenceUiCache {
    class Base : AdminScopeModule {

        private val navigationCommunication = NavigationCommunication.Base()
        private val sentenceUiCache = SentenceUiCache.Base()

        override fun provideNavigation() = navigationCommunication

        override fun provideSentenceUiCache() = sentenceUiCache
    }
}
