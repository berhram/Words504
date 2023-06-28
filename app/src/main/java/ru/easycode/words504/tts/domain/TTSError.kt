package ru.easycode.words504.tts.domain

import androidx.annotation.StringRes
import ru.easycode.words504.R
import ru.easycode.words504.presentation.ManageResources

interface TTSError {

    fun string(manageResources: ManageResources): String

    abstract class Abstract(
        @StringRes protected open val errorMessage: Int
    ) : TTSError {

        override fun string(manageResources: ManageResources) = manageResources.string(errorMessage)
    }

    class Error : Abstract(R.string.error)

    class ErrorNetworkTimeout : Abstract(R.string.error_network_timeout)

    class ErrorNetwork : Abstract(R.string.error_network)

    class ErrorInvalidRequest : Abstract(R.string.error_invalid_request)

    class ErrorNotInstalledVoice : Abstract(R.string.error_voice)

    class ErrorOutput : Abstract(R.string.error_output)

    class ErrorService : Abstract(R.string.error_service)

    class ErrorSynthesis : Abstract(R.string.error_synthesis)
}
