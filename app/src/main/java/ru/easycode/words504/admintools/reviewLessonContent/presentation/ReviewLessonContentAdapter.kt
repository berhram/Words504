package ru.easycode.words504.admintools.reviewLessonContent.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.words504.databinding.ReviewLessonContentItemBinding
import ru.easycode.words504.domain.Mapper
import ru.easycode.words504.presentation.ClickListener
import ru.easycode.words504.presentation.adapter.GenericViewHolder

class ReviewLessonContentAdapter(
    private val clickListener: ClickListener<ReviewLessonContentUi>
) : RecyclerView.Adapter<ReviewLessonContentViewHolder>(),
    Mapper.Unit<List<ReviewLessonContentUi>> {

    private val list = mutableListOf<ReviewLessonContentUi>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewLessonContentViewHolder {
        val view = ReviewLessonContentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewLessonContentViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ReviewLessonContentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun map(source: List<ReviewLessonContentUi>) {
        val diff = DiffCallBack(list, source)
        val result = DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }
}

class ReviewLessonContentViewHolder(
    private val binding: ReviewLessonContentItemBinding,
    private val clickListener: ClickListener<ReviewLessonContentUi>
) : GenericViewHolder<ReviewLessonContentUi>(binding.root) {

    override fun bind(item: ReviewLessonContentUi) {
        binding.nameTextView.text = item.id()
        itemView.setOnClickListener { clickListener.click(item) }
    }
}

class DiffCallBack(
    private val oldList: List<ReviewLessonContentUi>,
    private val newList: List<ReviewLessonContentUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id() == newList[newItemPosition].id()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
