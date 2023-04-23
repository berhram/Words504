package ru.easycode.words504.recognition.data

import androidx.annotation.StringRes
import ru.easycode.words504.R
import ru.easycode.words504.presentation.ManageResources

interface STTError {

    fun string(manageResources: ManageResources): String

    abstract class Abstract(
        @StringRes protected open val errorMessage: Int,
    ) : STTError {
        override fun string(manageResources: ManageResources) = manageResources.string(errorMessage)
    }

    class ErrorNone : Abstract(R.string.none)
    class ErrorNetworkTimeout : Abstract(R.string.error_network_timeout)
    class ErrorNetwork : Abstract(R.string.error_network)
    class ErrorAudio : Abstract(R.string.error_audio)
    class ErrorServer : Abstract(R.string.error_server)
    class ErrorClient : Abstract(R.string.error_client)
    class ErrorSpeechTimeout : Abstract(R.string.error_speech_timeout)
    class ErrorNoMatch : Abstract(R.string.error_no_match)
    class ErrorRecognizerBusy : Abstract(R.string.error_recognizer_busy)
    class ErrorInsufficientPermissions : Abstract(R.string.error_insufficient_permissions)
    class ErrorTooManyRequests : Abstract(R.string.error_too_many_request)
    class ErrorServerDisconnected : Abstract(R.string.error_server_disconnected)
    class ErrorLanguageNotSupported : Abstract(R.string.error_language_not_supported)
    class ErrorLanguageUnavailable : Abstract(R.string.error_language_unvaliable)
    class ErrorCannotCheckSupport : Abstract(R.string.error_cannot_check_support)
}

interface STTErrorsFactory {
    fun createError(id: Int): STTError

    class Base : STTErrorsFactory {
        override fun createError(id: Int): STTError = when (id) {
            0 -> STTError.ErrorNone()
            1 -> STTError.ErrorNetworkTimeout()
            2 -> STTError.ErrorNetwork()
            3 -> STTError.ErrorAudio()
            4 -> STTError.ErrorServer()
            5 -> STTError.ErrorClient()
            6 -> STTError.ErrorSpeechTimeout()
            7 -> STTError.ErrorNoMatch()
            8 -> STTError.ErrorRecognizerBusy()
            9 -> STTError.ErrorInsufficientPermissions()
            10 -> STTError.ErrorTooManyRequests()
            11 -> STTError.ErrorServerDisconnected()
            12 -> STTError.ErrorLanguageNotSupported()
            13 -> STTError.ErrorLanguageUnavailable()
            14 -> STTError.ErrorCannotCheckSupport()
            else -> throw java.lang.IllegalStateException("Unknown error source: $id")
        }
    }
}
