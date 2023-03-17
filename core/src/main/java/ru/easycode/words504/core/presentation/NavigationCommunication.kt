package ru.easycode.words504.core.presentation

import ru.easycode.words504.core.Screen
import ru.easycode.words504.presentation.Communication

interface NavigationCommunication {

    interface Update : Communication.Update<Screen>

    interface Observe : Communication.Observe<Screen>

    interface Mutable : Update, Observe

    class Base : Communication.Abstract<Screen>(), Mutable
}