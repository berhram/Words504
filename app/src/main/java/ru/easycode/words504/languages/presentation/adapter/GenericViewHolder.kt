package ru.easycode.words504.languages.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.words504.languages.presentation.LanguageUi

abstract class GenericViewHolder<T : LanguageUi>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)
}