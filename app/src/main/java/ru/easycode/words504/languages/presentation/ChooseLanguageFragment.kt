package ru.easycode.words504.languages.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.words504.R
import ru.easycode.words504.databinding.FragmentChooseLanguageBinding
import ru.easycode.words504.presentation.BaseFragment

class ChooseLanguageFragment :
    BaseFragment<ChooseLanguageViewModel.Base, FragmentChooseLanguageBinding>() {
    override val viewModelClass = ChooseLanguageViewModel.Base::class.java
    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChooseLanguageBinding =
        FragmentChooseLanguageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val saveButton = view.findViewById<Button>(R.id.save_view)
        val recyclerView = view.findViewById<RecyclerView>(R.id.choose_lang_view)
        val adapter = ChooseLanguageAdapter(object : ClickListener {
            override fun click(item: LanguageUi) = item.click(viewModel)
        })
        recyclerView.adapter = adapter
        viewModel.observe(this) {
            it.map(adapter, saveButton)
        }
        saveButton.setOnClickListener {
            viewModel.save()
        }
        viewModel.init(SaveAndRestoreLanguageCache.Base(savedInstanceState))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(SaveAndRestoreLanguageCache.Base(outState))
    }
}
