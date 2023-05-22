package ru.easycode.words504.admintools.lessonslist.presentation

import ru.easycode.words504.presentation.Communication

interface LessonsListCommunication : Communication.Mutable<LessonsUi> {

    class Base : Communication.Abstract<LessonsUi>(), LessonsListCommunication
}
