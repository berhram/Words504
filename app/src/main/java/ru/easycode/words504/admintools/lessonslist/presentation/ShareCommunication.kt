package ru.easycode.words504.admintools.lessonslist.presentation

import ru.easycode.words504.presentation.Communication

interface ShareCommunication : Communication.Mutable<String> {
    class Base : Communication.Abstract<String>(), ShareCommunication
}
