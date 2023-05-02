package ru.easycode.words504.tts

import android.widget.TextView

interface TTSTestUi {
    fun showSentence(textView: TextView)
    fun showAllSentences(textView: TextView)

    data class Base(
        private val sentence: String,
        private val listOfSentences: List<String>
    ) : TTSTestUi {
        override fun showSentence(textView: TextView) {
            textView.text = sentence
        }

        override fun showAllSentences(textView: TextView) {
            textView.text = listOfSentences.toString()
        }
    }
}
