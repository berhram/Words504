package ru.easycode.words504.tts.presentation

import android.widget.TextView

interface TTSState {

    fun show(messageView: TextView, startedView: TextView, finishedView: TextView)

    abstract class Abstract(private val message: String, private val started: String, private val finished: String) : TTSState {

        override fun show(messageView: TextView, startedView: TextView, finishedView: TextView) {
            messageView.text = message
            if (started.isNotEmpty()) startedView.text = started
            if (finished.isNotEmpty()) finishedView.text = finished
        }
    }

    data class Message(private val message: String) : Abstract(message, "", "")

    data class Finished(private val finished: String) : Abstract("", "", finished)

    data class Started(private val started: String) : Abstract("", started, "")
}
