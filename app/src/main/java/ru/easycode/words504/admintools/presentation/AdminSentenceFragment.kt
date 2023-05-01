package ru.easycode.words504.admintools.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.words504.databinding.FragmentAdminSentenceBinding
import ru.easycode.words504.presentation.BaseFragment

class AdminSentenceFragment :
    BaseFragment<AdminSentenceViewModel.Base, FragmentAdminSentenceBinding>() {

    override val viewModelClass = AdminSentenceViewModel.Base::class.java
    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAdminSentenceBinding = FragmentAdminSentenceBinding
        .inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adminFragmentToolbar.setNavigationOnClickListener { viewModel.navigateBack() }
        binding.adminAddButton.setOnClickListener { binding.wordsLinearLayout.add() }
        binding.adminSaveButton.setOnClickListener {
            val read = binding.wordsLinearLayout.read()
            with(binding.wordsLinearLayout) {
                removeAllViews()
                save(read)
            }
        }
    }
}
