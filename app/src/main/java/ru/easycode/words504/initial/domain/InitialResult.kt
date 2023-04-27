package ru.easycode.words504.initial.domain

import ru.easycode.words504.MainScreen
import ru.easycode.words504.initial.presentation.InitialCommunication
import ru.easycode.words504.initial.presentation.InitialUiState
import ru.easycode.words504.languages.presentation.ChooseLanguageScreen
import ru.easycode.words504.presentation.NavigationCommunication

interface InitialResult {

    fun map(communication: InitialCommunication, navigation: NavigationCommunication.Update)

    class NotFirstOpening : InitialResult {
        override fun map(
            communication: InitialCommunication,
            navigation: NavigationCommunication.Update
        ) {
            communication.map(InitialUiState.Loading)
            navigation.map(MainScreen)
        }
    }

    class FirstOpening : InitialResult {
        override fun map(
            communication: InitialCommunication,
            navigation: NavigationCommunication.Update
        ) {
            communication.map(InitialUiState.Loading)
            navigation.map(ChooseLanguageScreen)
        }
    }

    class Error(private val message: String) : InitialResult {
        override fun map(
            communication: InitialCommunication,
            navigation: NavigationCommunication.Update
        ) {
            communication.map(InitialUiState.Loading)
            communication.map(InitialUiState.Error(message))
        }
    }
}
