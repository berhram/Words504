package ru.easycode.words504.tts

import android.widget.TextView

interface TTSState {
    fun show(textView: TextView)

    abstract class Abstract(private val state: String) : TTSState {
        override fun show(textView: TextView) {
            textView.text = state
        }
    }

    class Started(state: String) : Abstract(state)

    class Finished(state: String) : Abstract(state)

    class Error(state: String) : Abstract(state)

    class Stopped(state: String) : Abstract(state)

    class PartialFinished(state: String) : Abstract(state)
}