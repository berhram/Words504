package ru.easycode.words504.loading.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.words504.databinding.ItemLoadCoroutinesBinding
import ru.easycode.words504.domain.Mapper

class LoadTranslationsAdapter :
    RecyclerView.Adapter<WordWithTranslationViewHolder>(), Mapper.Unit<List<ItemTranslateUi>> {

    private val list = mutableListOf<ItemTranslateUi>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordWithTranslationViewHolder {
        val itemBinding =
            ItemLoadCoroutinesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return WordWithTranslationViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WordWithTranslationViewHolder, position: Int) =
        holder.map(list[position])

    override fun getItemCount(): Int = list.size

    override fun map(source: List<ItemTranslateUi>) {
        val result = DiffUtil.calculateDiff(DiffCallBack(list, source))
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }
}

class DiffCallBack(
    private val oldList: List<ItemTranslateUi>,
    private val newList: List<ItemTranslateUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id() == newList[newItemPosition].id()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}

class WordWithTranslationViewHolder(
    private val itemBinding: ItemLoadCoroutinesBinding
) : RecyclerView.ViewHolder(itemBinding.root), Mapper<ItemTranslateUi, Unit> {

    override fun map(source: ItemTranslateUi) = source.show(itemBinding.translationText)
}
