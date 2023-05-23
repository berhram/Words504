package ru.easycode.words504.admintools.lessonslist.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.words504.databinding.AdminLessonItemBinding
import ru.easycode.words504.domain.Mapper
import ru.easycode.words504.presentation.adapter.AbstractDiffUtilCallback
import ru.easycode.words504.presentation.adapter.GenericViewHolder

interface AdminLessonsAdapter : Mapper.Unit<List<LessonUi>> {
    fun provideContext(): Context

    class Base(
        private val context: Context,
        private val clicksListener: LessonUiClickListener
    ) : RecyclerView.Adapter<AdminLessonsViewHolder>(), AdminLessonsAdapter {

        private val list = mutableListOf<LessonUi>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AdminLessonsViewHolder(
            AdminLessonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clicksListener
        )

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: AdminLessonsViewHolder, position: Int) {
            holder.bind(list[position])
        }

        override fun provideContext(): Context = context

        override fun map(source: List<LessonUi>) {
            val diff = DiffCallBack(list, source)
            val result = DiffUtil.calculateDiff(diff)
            list.clear()
            list.addAll(source)
            result.dispatchUpdatesTo(this)
        }
    }
}

class AdminLessonsViewHolder(
    private val binding: AdminLessonItemBinding,
    private val clicksListener: LessonUiClickListener
) : GenericViewHolder<LessonUi>(binding.root) {

    override fun bind(item: LessonUi) {
        with(binding) {
            item.map(stateIcon, nameTextView, stateTextView)
            itemView.setOnClickListener {
                clicksListener.click(item)
            }
            shareButton.setOnClickListener {
                clicksListener.share(item)
            }
        }
    }
}

class DiffCallBack(
    private val oldList: List<LessonUi>,
    private val newList: List<LessonUi>
) : AbstractDiffUtilCallback<LessonUi>(oldList, newList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id() == newList[newItemPosition].id()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
