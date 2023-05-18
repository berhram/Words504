package ru.easycode.words504.tts.presentation

import ru.easycode.words504.presentation.Communication

interface TTSCommunication : Communication.Mutable<TTSState> {

    class Base : Communication.Abstract<TTSState>(), TTSCommunication
}
