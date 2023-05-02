package ru.easycode.words504.recognition

import android.widget.TextView

interface STTState {

    fun show(textView: TextView)

    abstract class Abstract(private val state: String) : STTState {
        override fun show(textView: TextView) {
            textView.text = state
        }
    }

    class Started(state: String) : Abstract(state)

    class Finished(state: String) : Abstract(state)

    class Error(state: String) : Abstract(state)
}
