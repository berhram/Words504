package ru.easycode.words504.recognition.data

import android.content.Context
import android.speech.SpeechRecognizer

interface VoiceRecognition {
    fun create(context: Context): SpeechRecognizer
    fun init()
    fun record()
    fun stop()
    fun recogniseText()
    fun clear()

    class Base : VoiceRecognition {
        override fun create(context: Context): SpeechRecognizer =
            SpeechRecognizer.createSpeechRecognizer(context)

        override fun init() {
            TODO("Not yet implemented")
        }

        override fun record() {
            TODO("Not yet implemented")
        }

        override fun stop() {
            TODO("Not yet implemented")
        }

        override fun recogniseText() {
            TODO("Not yet implemented")
        }

        override fun clear() {
            TODO("Not yet implemented")
        }
    }
}