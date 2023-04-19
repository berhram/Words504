package ru.easycode.words504.loading

import android.os.Bundle
import ru.easycode.words504.databinding.ActivityLoadCoroutinesBinding
import ru.easycode.words504.presentation.BaseActivity
import ru.easycode.words504.loading.adapter.LoadTranslationsAdapter

class LoadTranslateActivity : BaseActivity<LoadTranslateViewModel>() {
    override val viewModelClass: Class<LoadTranslateViewModel> = LoadTranslateViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoadCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.translationsList.adapter = LoadTranslationsAdapter.Base()
        viewModel.observe(this) { result ->
            result.show(binding.progressTextView, binding.translationsList)
        }
    }
}
