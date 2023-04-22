package ru.easycode.words504.loading.adapter

import android.widget.TextView
import ru.easycode.words504.domain.Mapper

interface TranslateResultUi {

    fun show(
        progressTextView: TextView,
        adapter: Mapper.Unit<List<ItemTranslateUi>>
    )

    class Success(
        private val currentList: List<ItemTranslateUi>,
        private val maximumSize: Int
    ) : TranslateResultUi {

        override fun show(
            progressTextView: TextView,
            adapter: Mapper.Unit<List<ItemTranslateUi>>
        ) {
            progressTextView.text = "${currentList.size} out of $maximumSize"
            adapter.map(currentList)
        }
    }
}
