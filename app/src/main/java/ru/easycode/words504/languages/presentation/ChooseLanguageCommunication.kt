package ru.easycode.words504.languages.presentation

import ru.easycode.words504.presentation.Communication

interface ChooseLanguageCommunication : Communication.Mutable<ChooseLanguageState> {
    class Base : Communication.Abstract<ChooseLanguageState>(), ChooseLanguageCommunication
}
