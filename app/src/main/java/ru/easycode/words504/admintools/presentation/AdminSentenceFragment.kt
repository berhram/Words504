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
        binding.adminToolbarView.setNavigationOnClickListener { viewModel.goBack() }
        binding.adminAddButtonView.setOnClickListener { binding.wordsLinearLayoutView.add() }
        binding.adminSaveButtonView.setOnClickListener {
            viewModel.save(
                SentenceUi.Base(
                    binding.wordsIndexesInputView.editText?.text.toString(),
                    binding.wordsLinearLayoutView.read()
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
                binding.wordsIndexesInputView.editText?.text.toString(),
                binding.wordsLinearLayoutView.read()
            )
        )
    }

    override fun map(ui: String, words: List<WordUi>) {
        binding.wordsIndexesInputView.editText?.setText(ui)
        binding.wordsLinearLayoutView.save(words)
    }
}
