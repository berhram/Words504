package ru.easycode.words504.loading

import ru.easycode.words504.loading.adapter.TranslateResultUi
import ru.easycode.words504.presentation.Communication

interface LoadTranslateCommunication : Communication.Mutable<TranslateResultUi> {

    class Base : Communication.Abstract<TranslateResultUi>(), LoadTranslateCommunication
}
