package ru.easycode.words504.languages.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.words504.databinding.LanguageItemBinding
import ru.easycode.words504.domain.Mapper
import ru.easycode.words504.languages.presentation.adapter.GenericViewHolder
import ru.easycode.words504.presentation.ClickListener

class ChooseLanguageAdapter(private val clickListener: ClickListener<LanguageUi>) :
    RecyclerView.Adapter<ChooseLanguageViewHolder>(), Mapper.Unit<List<LanguageUi>> {

    private val list = mutableListOf<LanguageUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChooseLanguageViewHolder(
        LanguageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
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
    private val binding: LanguageItemBinding, private val clickListener: ClickListener<LanguageUi>
) : GenericViewHolder<LanguageUi>(binding.root) {

    override fun bind(item: LanguageUi) {
        item.map(binding.languageTextView)
        itemView.setOnClickListener {
            clickListener.click(item)
        }
    }
}

class DiffUtilCallback(
    private val oldList: List<LanguageUi>, private val newList: List<LanguageUi>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id() == newList[newItemPosition].id()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].equals(newList[newItemPosition])
}
