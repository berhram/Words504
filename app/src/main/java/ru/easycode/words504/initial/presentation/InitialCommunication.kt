package ru.easycode.words504.initial.presentation

import ru.easycode.words504.presentation.Communication

interface InitialCommunication : Communication.Mutable<InitialUiState>{

    class Base : Communication.Abstract<InitialUiState>(), InitialCommunication
}
