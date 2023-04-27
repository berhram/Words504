package ru.easycode.words504.sl

import ru.easycode.words504.presentation.NavigationCommunication

interface ProvideNavigation {
    fun provideNavigation(): NavigationCommunication.Mutable
}
