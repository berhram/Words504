package ru.easycode.words504.recognition.data

import android.os.Bundle
import android.speech.RecognitionListener

abstract class SimpleRecognitionListener : RecognitionListener {
    override fun onReadyForSpeech(params: Bundle?) = Unit

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(rmsdB: Float) = Unit

    override fun onBufferReceived(buffer: ByteArray?) = Unit

    override fun onEndOfSpeech() = Unit

    override fun onError(error: Int) = Unit

    override fun onResults(results: Bundle?) = Unit

    override fun onPartialResults(partialResults: Bundle?) = Unit

    override fun onEvent(eventType: Int, params: Bundle?) = Unit
}
