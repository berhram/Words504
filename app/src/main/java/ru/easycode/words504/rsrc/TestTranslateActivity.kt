package ru.easycode.words504.rsrc

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.words504.R
import ru.easycode.words504.databinding.ActivityTestCoroutinesBinding
import ru.easycode.words504.presentation.BaseActivity
import ru.easycode.words504.rsrc.adapter.TestTranslationsAdapter

class TestTranslateActivity : BaseActivity<TestTranslateViewModel>() {
    override val viewModelClass: Class<TestTranslateViewModel> = TestTranslateViewModel::class.java

    private lateinit var binding: ActivityTestCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TestTranslationsAdapter()
        binding.translationsList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        viewModel.observe(this@TestTranslateActivity) {
            adapter.map(it.currentList())
            binding.progressText.text =
                getString(R.string.progress_text, it.current(), it.maximum())
            binding.translationsList.smoothScrollToPosition(adapter.itemCount)
        }
    }
}
