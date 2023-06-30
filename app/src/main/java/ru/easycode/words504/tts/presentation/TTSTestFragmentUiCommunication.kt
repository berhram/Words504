package ru.easycode.words504.tts.presentation

import ru.easycode.words504.presentation.Communication

interface TTSTestFragmentUiCommunication : Communication.Mutable<TTSTestFragmentUIState> {
    class Base :
        Communication.Abstract<TTSTestFragmentUIState>(),
        TTSTestFragmentUiCommunication
}
