package ru.easycode.words504.tts

import ru.easycode.words504.presentation.Communication

interface TTSCommunication {
    class Base : Communication.Abstract<List<String>>()
}