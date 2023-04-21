package ru.easycode.words504.loading

import android.os.Bundle
import ru.easycode.words504.databinding.ActivityLoadCoroutinesBinding
import ru.easycode.words504.loading.adapter.LoadTranslationsAdapter
import ru.easycode.words504.presentation.BaseActivity

class LoadTranslateActivity : BaseActivity<LoadTranslateViewModel>() {
    override val viewModelClass: Class<LoadTranslateViewModel> = LoadTranslateViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoadCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = LoadTranslationsAdapter()
        with(binding) {
            translationsList.adapter = adapter
            viewModel.observe(this@LoadTranslateActivity) { result ->
                result.show(progressTextView, adapter)
                translationsList.smoothScrollToPosition(adapter.itemCount)
            }
        }
    }
}
