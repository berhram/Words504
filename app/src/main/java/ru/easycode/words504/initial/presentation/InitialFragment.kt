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
        container: ViewGroup?,
    ): FragmentInitialBinding = FragmentInitialBinding.inflate(
        inflater,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.error.setRetryListener {
            viewModel.retry()
        }
        viewModel.observe(this) { state ->
            with(binding) {
                error.visibility = View.INVISIBLE
                progress.visibility = View.INVISIBLE
                when (state) {
                    is InitialUiState.Loading -> state.apply(progress)
                    is InitialUiState.Error -> state.apply(error)
                }
            }
        }
        viewModel.init()
    }
}
