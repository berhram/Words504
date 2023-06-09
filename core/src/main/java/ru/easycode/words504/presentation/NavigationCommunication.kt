package ru.easycode.words504.presentation

interface NavigationCommunication {

    interface Update : Communication.Update<Screen>

    interface Observe : Communication.Observe<Screen>

    interface Mutable : Update, Observe

    class Base : Communication.Abstract<Screen>(), Mutable
}
