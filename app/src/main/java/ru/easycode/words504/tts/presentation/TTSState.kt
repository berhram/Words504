package ru.easycode.words504.tts.presentation

import android.widget.TextView

interface TTSState {

    fun show(lastFinished: TextView, message: TextView)

    abstract class Abstract(private val last: String, private val messageText: String) : TTSState {

        override fun show(lastFinished: TextView, message: TextView) {
            lastFinished.text = last
            message.text = messageText
        }
    }

    class Finished(last: String, message: String) : Abstract(last, message)
}
