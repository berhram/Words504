package ru.easycode.words504.recognition.domain

import ru.easycode.words504.R
import ru.easycode.words504.domain.HandleError
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.recognition.data.STTErrors

class STTHandleError() : HandleError<Int, STTErrors> {

    override fun handle(source: Int): STTErrors =
        when (source) {
            STTErrors.ERROR_NETWORK_TIMEOUT.ordinal -> STTErrors.ERROR_NETWORK_TIMEOUT
            STTErrors.ERROR_NETWORK.ordinal -> STTErrors.ERROR_NETWORK
            STTErrors.ERROR_AUDIO.ordinal -> STTErrors.ERROR_AUDIO
            STTErrors.ERROR_CLIENT.ordinal -> STTErrors.ERROR_CLIENT
            STTErrors.ERROR_SERVER.ordinal -> STTErrors.ERROR_SERVER
            STTErrors.ERROR_SERVER_DISCONNECTED.ordinal -> STTErrors.ERROR_SERVER_DISCONNECTED
            STTErrors.ERROR_NO_MATCH.ordinal -> STTErrors.ERROR_NO_MATCH
            STTErrors.ERROR_SPEECH_TIMEOUT.ordinal -> STTErrors.ERROR_SPEECH_TIMEOUT
            STTErrors.ERROR_RECOGNIZER_BUSY.ordinal -> STTErrors.ERROR_RECOGNIZER_BUSY
            STTErrors.ERROR_INSUFFICIENT_PERMISSIONS.ordinal -> STTErrors.ERROR_INSUFFICIENT_PERMISSIONS
            STTErrors.ERROR_TOO_MANY_REQUESTS.ordinal -> STTErrors.ERROR_TOO_MANY_REQUESTS
            STTErrors.ERROR_LANGUAGE_NOT_SUPPORTED.ordinal -> STTErrors.ERROR_LANGUAGE_NOT_SUPPORTED
            STTErrors.ERROR_LANGUAGE_UNAVAILABLE.ordinal -> STTErrors.ERROR_LANGUAGE_UNAVAILABLE
            STTErrors.ERROR_CANNOT_CHECK_SUPPORT.ordinal -> STTErrors.ERROR_CANNOT_CHECK_SUPPORT
            else -> {
                STTErrors.NONE
            }
        }
}