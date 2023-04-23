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
            ERROR_NONE -> STTError.ErrorNone()
            ERROR_NETWORK_TIMEOUT -> STTError.ErrorNetworkTimeout()
            ERROR_NETWORK -> STTError.ErrorNetwork()
            ERROR_AUDIO -> STTError.ErrorAudio()
            ERROR_SERVER -> STTError.ErrorServer()
            ERROR_CLIENT -> STTError.ErrorClient()
            ERROR_SPEECH_TIMEOUT -> STTError.ErrorSpeechTimeout()
            ERROR_NO_MATCH -> STTError.ErrorNoMatch()
            ERROR_RECOGNIZER_BUSY -> STTError.ErrorRecognizerBusy()
            ERROR_INSUFFICIENT_PERMISSIONS -> STTError.ErrorInsufficientPermissions()
            ERROR_TOO_MANY_REQUESTS -> STTError.ErrorTooManyRequests()
            ERROR_SERVER_DISCONNECTED -> STTError.ErrorServerDisconnected()
            ERROR_LANGUAGE_NOT_SUPPORTED -> STTError.ErrorLanguageNotSupported()
            ERROR_LANGUAGE_UNAVAILABLE -> STTError.ErrorLanguageUnavailable()
            ERROR_LANGUAGE_CANNOT_CHECK_SUPPORT -> STTError.ErrorCannotCheckSupport()
            else -> throw java.lang.IllegalStateException("Unknown error source: $id")
        }
    }

    companion object {
        private const val ERROR_NONE = 0
        private const val ERROR_NETWORK_TIMEOUT = 1
        private const val ERROR_NETWORK = 2
        private const val ERROR_AUDIO = 3
        private const val ERROR_SERVER = 4
        private const val ERROR_CLIENT = 5
        private const val ERROR_SPEECH_TIMEOUT = 6
        private const val ERROR_NO_MATCH = 7
        private const val ERROR_RECOGNIZER_BUSY = 8
        private const val ERROR_INSUFFICIENT_PERMISSIONS = 9
        private const val ERROR_TOO_MANY_REQUESTS = 10
        private const val ERROR_SERVER_DISCONNECTED = 11
        private const val ERROR_LANGUAGE_NOT_SUPPORTED = 12
        private const val ERROR_LANGUAGE_UNAVAILABLE = 13
        private const val ERROR_LANGUAGE_CANNOT_CHECK_SUPPORT = 14
    }
}
