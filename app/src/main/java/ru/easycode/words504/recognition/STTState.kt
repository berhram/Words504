package ru.easycode.words504.recognition

import android.widget.TextView

interface STTState {

    fun show(textView: TextView)

    abstract class Abstract(private val state: String) : STTState {
        override fun show(textView: TextView) {
            textView.text = state
        }
    }

    data class Started(private val state: String) : Abstract(state)

    data class Finished(private val state: String) : Abstract(state)

    data class Error(private val state: String) : Abstract(state)


}
