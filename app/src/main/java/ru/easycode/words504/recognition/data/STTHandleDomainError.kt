package ru.easycode.words504.recognition.data

import ru.easycode.words504.domain.HandleError
import ru.easycode.words504.presentation.ManageResources

class STTHandleDomainError(private val manageResources: ManageResources): HandleError<STTErrors, String> {
    override fun handle(source: STTErrors): String {
        return ""
        /*when (source) {
            STTErrors.ERROR_NETWORK_TIMEOUT ->
            STTErrors.ERROR_NETWORK ->
            STTErrors.ERROR_AUDIO ->
            STTErrors.ERROR_CLIENT ->
            STTErrors.ERROR_SERVER ->
            STTErrors.ERROR_SERVER_DISCONNECTED ->
            STTErrors.ERROR_NO_MATCH ->
            STTErrors.ERROR_SPEECH_TIMEOUT ->
            STTErrors.ERROR_RECOGNIZER_BUSY ->
            STTErrors.ERROR_INSUFFICIENT_PERMISSIONS ->
            STTErrors.ERROR_TOO_MANY_REQUESTS ->
            STTErrors.ERROR_LANGUAGE_NOT_SUPPORTED ->
            STTErrors.ERROR_LANGUAGE_UNAVAILABLE ->
            STTErrors.ERROR_CANNOT_CHECK_SUPPORT ->
        }*/
    }

}