package ru.easycode.words504.tts.presentation

import ru.easycode.words504.presentation.Communication

interface TTSStateCommunication : Communication.Mutable<TTSState> {

    class Base : Communication.Abstract<TTSState>(), TTSStateCommunication
}



