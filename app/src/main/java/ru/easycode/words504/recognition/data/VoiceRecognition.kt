package ru.easycode.words504.recognition.data

import android.content.Context
import android.speech.SpeechRecognizer

interface VoiceRecognition {
    fun create(context: Context): SpeechRecognizer

    class Base : VoiceRecognition {
        override fun create(context: Context): SpeechRecognizer =
            SpeechRecognizer.createSpeechRecognizer(context)
    }
}