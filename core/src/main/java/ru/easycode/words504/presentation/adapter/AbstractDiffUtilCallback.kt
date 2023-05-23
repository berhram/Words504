package ru.easycode.words504.presentation.adapter

import androidx.recyclerview.widget.DiffUtil

abstract class AbstractDiffUtilCallback<T : Any>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}
