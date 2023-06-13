package ru.easycode.words504.admintools.lessonslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.words504.databinding.FragmentAdminLessonsListBinding
import ru.easycode.words504.presentation.BaseFragment

class AdminLessonsListFragment :
    BaseFragment<AdminLessonsListViewModel.Base, FragmentAdminLessonsListBinding>() {

    override val viewModelClass = AdminLessonsListViewModel.Base::class.java

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAdminLessonsListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AdminLessonsAdapter.Base(
            object : LessonUiClickListener {
                override fun share(item: LessonUi) {
                    viewModel.share(item.id())
                }

                override fun click(item: LessonUi) {
                    viewModel.chooseLesson(item.id())
                }
            }
        )
        viewModel.observe(this) {
            it.map(adapter, requireContext())
        }
        viewModel.init()
        with(binding) {
            lessonsRecycler.adapter = adapter
            addButton.setOnClickListener { viewModel.addLesson() }
        }
        (requireActivity() as AppCompatActivity).title = viewModel.title()
    }
}
