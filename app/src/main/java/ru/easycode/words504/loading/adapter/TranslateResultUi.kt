package ru.easycode.words504.loading.adapter

import android.widget.TextView
import androidx.core.view.isVisible
import ru.easycode.words504.domain.Mapper
import ru.easycode.words504.views.ProgressView

interface TranslateResultUi {

    fun show(
        progressView: ProgressView,
        progressTextView: TextView,
        adapter: Mapper.Unit<List<ItemTranslateUi>>
    )

    class Success(
        private val currentList: List<ItemTranslateUi>,
        private val maximumSize: Int
    ) : TranslateResultUi {

        override fun show(
            progressView: ProgressView,
            progressTextView: TextView,
            adapter: Mapper.Unit<List<ItemTranslateUi>>
        ) {
            if (currentList.size == maximumSize) {
                progressView.isVisible = false
            }
            progressTextView.text = "${currentList.size} out of $maximumSize"
            adapter.map(currentList)
        }
    }
}
