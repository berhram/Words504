package ru.easycode.words504.tts.presentation

import ru.easycode.words504.presentation.Communication

interface TTSResultCommunicationStates {

    interface Update : Communication.Update<TTSState>

    interface Observe : Communication.Observe<TTSState>

    interface Mutable : Update, Observe

    class Base : Communication.Abstract<TTSState>(), Mutable
}
