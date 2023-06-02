package ru.easycode.words504.admintools.reviewLessonContent.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.easycode.words504.admintools.data.cloud.ExerciseType
import ru.easycode.words504.databinding.FragmentLessonReviewContentBinding
import ru.easycode.words504.presentation.BaseFragment
import ru.easycode.words504.presentation.ManageResources

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

    override fun onStart() {
        super.onStart()
        Log.d("TEST", "ReviewLessonContentFragment")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(savedInstanceState == null)
        // TODO refactor this
        viewModel.observe(this) {
            Log.d("TEST", it.toString())
        }
        // TODO emulating click on content item, remove this later
        lifecycleScope.launch {
            delay(3000)
            // TODO move to adapter click item callback
            viewModel.choseContent(
                ReviewLessonContentUi.Exercise(
                    ManageResources.Base(requireContext()),
                    ExerciseType.FILL_BLANK
                )
            )
        }
    }
}
