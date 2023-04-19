package ru.easycode.words504.loading.adapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface TranslateResultUi {

    fun show(progressTextView: TextView, translateRecyclerView: RecyclerView)

    class Success(
        private val currentList: List<ItemTranslateUi>,
        private val maximumSize: Int
    ) : TranslateResultUi {

        override fun show(progressTextView: TextView, translateRecyclerView: RecyclerView) {
            progressTextView.text = "${currentList.size} out of $maximumSize"
            (translateRecyclerView.adapter as LoadTranslationsAdapter).map(currentList)
            translateRecyclerView.smoothScrollToPosition(currentList.size)
        }
    }
}
