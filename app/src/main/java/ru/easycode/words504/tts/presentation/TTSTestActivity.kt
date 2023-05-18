package ru.easycode.words504.tts.presentation

import android.os.Bundle
import ru.easycode.words504.databinding.ActivityTtsBinding
import ru.easycode.words504.presentation.BaseActivity

class TTSTestActivity : BaseActivity<TTSViewModel.Base>() {

    override val viewModelClass: Class<TTSViewModel.Base> = TTSViewModel.Base::class.java
    private lateinit var binding: ActivityTtsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTtsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.init {
            binding.buttonSpeak.isEnabled = true
        }
        binding.buttonSpeak.setOnClickListener {
            viewModel.ttsString(binding.textInputEditText.text.toString())
        }
        binding.buttonSpeak.setOnClickListener {
            viewModel.ttsString(binding.textInputEditText.text.toString())
        }
        viewModel.observeTTSResult(this) {
            it.show(binding.lastTextView)
        }
    }
}
