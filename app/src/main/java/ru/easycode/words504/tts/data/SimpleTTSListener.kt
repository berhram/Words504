package ru.easycode.words504.tts.data

import android.speech.tts.UtteranceProgressListener

abstract class SimpleTTSListener : UtteranceProgressListener() {
    override fun onStart(p0: String?) = Unit
    override fun onDone(p0: String?) = Unit
    override fun onError(utteranceId: String?, errorCode: Int) = Unit
    override fun onStop(utteranceId: String?, interrupted: Boolean) = Unit
}