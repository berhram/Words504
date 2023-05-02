package ru.easycode.words504.admintools.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.words504.databinding.FragmentAdminSentenceBinding
import ru.easycode.words504.presentation.BaseFragment

class AdminSentenceFragment :
    BaseFragment<SentenceViewModel.Base, FragmentAdminSentenceBinding>(), SentenceUi.Mapper<Unit> {

    override val viewModelClass = SentenceViewModel.Base::class.java

    override fun fragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAdminSentenceBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adminFragmentToolbar.setNavigationOnClickListener { viewModel.goBack() }
        binding.adminAddButton.setOnClickListener { binding.wordsLinearLayout.add() }
        binding.adminSaveButton.setOnClickListener {
            viewModel.save(
                SentenceUi.Base(
                    binding.wordsIndexesInputLayout.editText?.text.toString(),
                    binding.wordsLinearLayout.read()
                )
            )
        }
        viewModel.observe(this) {
            it.map(this)
        }
        viewModel.init(SaveAndRestoreSentenceUi.Base(savedInstanceState))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(
            SaveAndRestoreSentenceUi.Base(outState),
            SentenceUi.Base(
                binding.wordsIndexesInputLayout.editText?.text.toString(),
                binding.wordsLinearLayout.read()
            )
        )
    }

    override fun map(ui: String, words: List<WordUi>) {
        binding.wordsIndexesInputLayout.editText?.setText(ui)
        binding.wordsLinearLayout.save(words)
    }
}
