package ru.easycode.words504.tts

import android.widget.TextView
import java.util.Date

interface TTSState {
    fun show(textView: TextView)

    abstract class Abstract(private val text: String) : TTSState {
        override fun show(textView: TextView) {
            textView.text = text
        }
    }

    class Finished : Abstract("${Date()}")
}
