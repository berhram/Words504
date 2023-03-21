package ru.easycode.words504.tts

import ru.easycode.words504.presentation.Communication

interface TTSCommunication {

    interface Update : Communication.Update<List<String>>

    class Base : Communication.Abstract<List<String>>(), Update
}
