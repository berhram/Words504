package ru.easycode.words504.tts

import ru.easycode.words504.presentation.Communication

interface TTSCommunication {

    interface Update : Communication.Update<List<String>>

    interface Observe : Communication.Observe<List<String>>

    interface Mutable : Update, Observe

    class Base : Communication.Abstract<List<String>>(), Mutable
}
