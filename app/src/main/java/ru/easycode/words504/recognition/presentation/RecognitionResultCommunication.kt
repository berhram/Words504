package ru.easycode.words504.recognition.presentation

import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.recognition.STTState

interface RecognitionResultCommunication {

    interface Update : Communication.Update<STTState>

    interface Observe : Communication.Observe<STTState>

    interface Mutable : Update, Observe

    class Base : Communication.Abstract<STTState>(), Mutable
}

