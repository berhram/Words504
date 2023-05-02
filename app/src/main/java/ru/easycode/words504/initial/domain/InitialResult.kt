package ru.easycode.words504.initial.domain

import ru.easycode.words504.MainScreen
import ru.easycode.words504.initial.presentation.InitialCommunication
import ru.easycode.words504.initial.presentation.InitialUiState
import ru.easycode.words504.languages.presentation.ChooseLanguageScreen
import ru.easycode.words504.presentation.NavigationCommunication

interface InitialResult {

    fun map(communication: InitialCommunication)
    fun map(navigation: NavigationCommunication.Update)

    object NotFirstOpening : InitialResult {
        override fun map(navigation: NavigationCommunication.Update) = navigation.map(MainScreen)

        override fun map(communication: InitialCommunication) = Unit
    }

    object FirstOpening : InitialResult {
        override fun map(navigation: NavigationCommunication.Update) =
            navigation.map(ChooseLanguageScreen)

        override fun map(communication: InitialCommunication) = Unit
    }

    data class Error(private val message: String) : InitialResult {
        override fun map(communication: InitialCommunication) =
            communication.map(InitialUiState.Error(message))

        override fun map(navigation: NavigationCommunication.Update) = Unit
    }
}
