package ru.easycode.words504.recognition.data

import ru.easycode.words504.R
import ru.easycode.words504.presentation.ManageResources

enum class STTErrors(private val number: Int, private val resourceId: Int) {
    ERROR_NETWORK_TIMEOUT(1, R.string.error_network_timeout),
    ERROR_NETWORK(2, R.string.error_network),
    ERROR_AUDIO(3, R.string.error_audio),
    ERROR_SERVER(4, R.string.error_server),
    ERROR_CLIENT(5, R.string.error_client),
    ERROR_SPEECH_TIMEOUT(6, R.string.error_speech_timeout),
    ERROR_NO_MATCH(7, R.string.error_no_match),
    ERROR_RECOGNIZER_BUSY(8, R.string.error_recognizer_busy),
    ERROR_INSUFFICIENT_PERMISSIONS(9, R.string.error_insufficient_permissions),
    ERROR_TOO_MANY_REQUESTS(10, R.string.error_too_many_request),
    ERROR_SERVER_DISCONNECTED(11, R.string.error_server_disconnected),
    ERROR_LANGUAGE_NOT_SUPPORTED(12, R.string.error_language_not_supported),
    ERROR_LANGUAGE_UNAVAILABLE(13, R.string.error_language_unvaliable),
    ERROR_CANNOT_CHECK_SUPPORT(14, R.string.error_cannot_check_support),
    NONE(0, R.string.none);

    fun string(manageResources: ManageResources) = manageResources.string(resourceId)

    companion object {
        fun state(number: Int): STTErrors = values().find { it.number == number }!!
    }
}