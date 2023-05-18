package ru.easycode.words504.tts.presentation

import android.widget.TextView

interface TTSState {

    fun show(messageView: TextView)

    abstract class Abstract(private val message: String) : TTSState {

        override fun show(messageView: TextView) {
            messageView.text = message
        }
    }

    data class Finished(private val message: String) : Abstract(message)
}
