package ru.easycode.words504.admintools.reviewLessonContent.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.words504.databinding.FragmentLessonReviewContentBinding
import ru.easycode.words504.presentation.BaseFragment
import ru.easycode.words504.presentation.ClickListener

/**
 * @author Asatryan on 22.05.2023
 */
class ReviewLessonContentFragment :
    BaseFragment<ReviewLessonContentViewModel.Base, FragmentLessonReviewContentBinding>() {
    override val viewModelClass = ReviewLessonContentViewModel.Base::class.java

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLessonReviewContentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ReviewLessonContentAdapter(object : ClickListener<ReviewLessonContentUi> {
            override fun click(item: ReviewLessonContentUi) {
                viewModel.choseContent(item)
            }
        })
        binding.recycler.adapter = adapter

        viewModel.init()
        viewModel.observe(this) { state ->
            state.map(adapter)
        }
    }
}
