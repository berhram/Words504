package ru.easycode.words504.recognition.data

import ru.easycode.words504.R
import ru.easycode.words504.presentation.ManageResources

enum class STTErrors(private val number: Int, private val resourceId: Int) {
    ERROR_NETWORK_TIMEOUT(1, R.string.error_network_timeout),
    ERROR_NETWORK(2, R.string.error_network),
    ERROR_AUDIO(3, R.string.error_audio),
    ERROR_SERVER(4),
    ERROR_CLIENT(5),
    ERROR_SPEECH_TIMEOUT(6),
    ERROR_NO_MATCH(7),
    ERROR_RECOGNIZER_BUSY(8),
    ERROR_INSUFFICIENT_PERMISSIONS(9),
    ERROR_TOO_MANY_REQUESTS(10),
    ERROR_SERVER_DISCONNECTED(11),
    ERROR_LANGUAGE_NOT_SUPPORTED(12),
    ERROR_LANGUAGE_UNAVAILABLE(13),
    ERROR_CANNOT_CHECK_SUPPORT(14),
    NONE(0);

    fun string(manageResources: ManageResources) = manageResources.string(resourceId)

    companion object {
        fun state(number: Int): STTErrors = values().find { it.number == number }!!
    }
}