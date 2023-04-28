package ru.easycode.words504.languages.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.words504.R

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
        list.clear()
        list.addAll(source)
        notifyDataSetChanged()
    }

}

class ChooseLanguageViewHolder(view: View, private val clickListener: ClickListener) :
    RecyclerView.ViewHolder(view) {
    private val lang = itemView.findViewById<TextView>(R.id.tv)
    fun bind(model: LanguageUi) {
        model.map(lang)
        itemView.setOnClickListener {
            clickListener.click(model)
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