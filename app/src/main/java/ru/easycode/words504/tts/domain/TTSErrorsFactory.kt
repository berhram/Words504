package ru.easycode.words504.tts.domain

import android.speech.tts.TextToSpeech

interface TTSErrorsFactory {

    fun createError(id: Int): TTSError

    class Base : TTSErrorsFactory {

        override fun createError(id: Int): TTSError = when (id) {
            TextToSpeech.ERROR -> TTSError.Error()
            TextToSpeech.ERROR_NETWORK_TIMEOUT -> TTSError.ErrorNetworkTimeout()
            TextToSpeech.ERROR_NETWORK -> TTSError.ErrorNetwork()
            TextToSpeech.ERROR_INVALID_REQUEST -> TTSError.ErrorInvalidRequest()
            TextToSpeech.ERROR_NOT_INSTALLED_YET -> TTSError.ErrorNotInstalledVoice()
            TextToSpeech.ERROR_OUTPUT -> TTSError.ErrorOutput()
            TextToSpeech.ERROR_SERVICE -> TTSError.ErrorService()
            TextToSpeech.ERROR_SYNTHESIS -> TTSError.ErrorSynthesis()
            else -> throw IllegalArgumentException("Unknown error ID: $id")
        }
    }
}
