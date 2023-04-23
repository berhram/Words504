package ru.easycode.words504.loading.adapter

import android.widget.TextView

interface ItemTranslateUi {

    fun id(): Int

    fun show(textView: TextView)

    data class Base(
        private val id: Int,
        private val wordWithTranslate: String
    ) : ItemTranslateUi {

        override fun id(): Int = id
        override fun show(textView: TextView) {
            textView.text = wordWithTranslate
        }
    }
}
