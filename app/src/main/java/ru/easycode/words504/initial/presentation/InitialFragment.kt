package ru.easycode.words504.initial.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.words504.databinding.FragmentInitialBinding
import ru.easycode.words504.presentation.BaseFragment

class InitialFragment : BaseFragment<InitialViewModel, FragmentInitialBinding>() {
    override val viewModelClass: Class<InitialViewModel> = InitialViewModel::class.java

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInitialBinding = FragmentInitialBinding.inflate(
        inflater,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.errorView.addRetryListener {
            viewModel.retry()
        }
        viewModel.observe(this) { state ->
            with(binding) {
                state.apply(errorView, progressView)
            }
        }
        viewModel.init()
    }
}
