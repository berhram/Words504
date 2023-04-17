package ru.easycode.words504.rsrc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.words504.databinding.ItemTestCoroutinesBinding
import ru.easycode.words504.domain.Mapper

class TestTranslationsAdapter : RecyclerView.Adapter<WordWithTranslationViewHolder>(),
    Mapper.Unit<List<String>> {

    private val list = mutableListOf<String>()
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): WordWithTranslationViewHolder {
        val itemBinding =
            ItemTestCoroutinesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordWithTranslationViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: WordWithTranslationViewHolder, position: Int) =
        holder.map(list[position])

    override fun getItemCount(): Int = list.size

    override fun map(source: List<String>) {
        val result = DiffUtil.calculateDiff(DiffCallBack(list, source))
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }
}

class DiffCallBack(
    private val oldList: List<String>, private val newList: List<String>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode() &&
            areContentsTheSame(oldItemPosition, newItemPosition)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}

class WordWithTranslationViewHolder(
    private val itemBinding: ItemTestCoroutinesBinding
) : RecyclerView.ViewHolder(itemBinding.root), Mapper<String, Unit> {

    override fun map(source: String) {
        itemBinding.translationText.text = source
    }
}
