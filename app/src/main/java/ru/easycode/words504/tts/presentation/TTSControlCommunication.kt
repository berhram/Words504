package ru.easycode.words504.tts.presentation

import ru.easycode.words504.presentation.Communication

interface TTSControlCommunication {

    interface Update : Communication.Update<Unit>

    interface Observe : Communication.Observe<Unit>

    interface Mutable : Update, Observe

    class Base : Communication.Abstract<Unit>(), Mutable
}
