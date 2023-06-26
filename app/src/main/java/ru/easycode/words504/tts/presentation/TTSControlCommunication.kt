package ru.easycode.words504.tts.presentation

import ru.easycode.words504.presentation.Communication

interface TTSControlCommunication {

    interface Update : Communication.Update<TTSControlState>

    interface Observe : Communication.Observe<TTSControlState>

    interface Mutable : Update, Observe

    class Base : Communication.Abstract<TTSControlState>(), Mutable
}
