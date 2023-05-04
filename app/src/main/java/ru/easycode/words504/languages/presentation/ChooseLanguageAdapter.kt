package ru.easycode.words504.languages.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.words504.R
import ru.easycode.words504.domain.Mapper
import ru.easycode.words504.languages.presentation.adapter.GenericViewHolder

class ChooseLanguageAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<ChooseLanguageViewHolder>(), Mapper.Unit<List<LanguageUi>> {

    private val list = mutableListOf<LanguageUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChooseLanguageViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.language_item, parent, false),
        clickListener
    )

    override fun onBindViewHolder(holder: ChooseLanguageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    override fun map(source: List<LanguageUi>) {
        val diff = DiffUtilCallback(list, source)
        val result = DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }
}

class ChooseLanguageViewHolder(
    private val view: View,
    private val clickListener: ClickListener
) : GenericViewHolder<LanguageUi>(view) {

    override fun bind(item: LanguageUi) = with(view) {
        item.map(findViewById(R.id.text_view))
        itemView.setOnClickListener {
            clickListener.click(item)
        }
    }
}

interface ClickListener {
    fun click(item: LanguageUi)
}

class DiffUtilCallback(
    private val oldList: List<LanguageUi>,
    private val newList: List<LanguageUi>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id() == newList[newItemPosition].id()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].equals(newList[newItemPosition])
}
