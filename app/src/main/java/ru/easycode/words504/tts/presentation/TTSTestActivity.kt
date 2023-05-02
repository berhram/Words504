package ru.easycode.words504.tts.presentation

import android.os.Bundle
import ru.easycode.words504.R
import ru.easycode.words504.databinding.ActivityTtsBinding
import ru.easycode.words504.presentation.BaseActivity
import ru.easycode.words504.tts.TTSState

class TTSTestActivity : BaseActivity<TTSTestViewModelFinal>() {

    override val viewModelClass: Class<TTSTestViewModelFinal> = TTSTestViewModelFinal::class.java
    private lateinit var binding: ActivityTtsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTtsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.init {
            binding.buttonSpeak.isEnabled = true
            binding.buttonSpeakBySentences.isEnabled = true
        }

        binding.buttonSpeak.setOnClickListener {
            binding.buttonStop.isEnabled = true
            viewModel.ttsString(binding.textInputEditText.text.toString())
        }

        binding.buttonSpeak.setOnClickListener {
            viewModel.ttsString(binding.textInputEditText.text.toString())
            binding.buttonStop.isEnabled = true
        }

        binding.buttonSpeakBySentences.setOnClickListener {
            viewModel.ttsStringList(
                binding.textInputEditText.text.toString().split("[.!?]".toRegex())
            )
            binding.buttonStop.isEnabled = true
        }

        binding.buttonStop.setOnClickListener {
            viewModel.stop()
        }

        // todo перенести логику отсюда
        viewModel.observeTTSResult(this) {
            when (it) {
                is TTSState.Started -> {
                    binding.buttonStop.isEnabled = true
                    it.show(binding.currentPhraseTextView)
                    binding.currentTitle.text = getString(R.string.current_text)
                }
                is TTSState.Finished -> {
                    binding.currentPhraseTextView.text = getString(R.string.end_of_text)
                    binding.buttonStop.isEnabled = false
                }
                is TTSState.PartialFinished -> {
                    it.show(binding.previousPhraseTextView)
                    binding.previousTitle.text = getString(R.string.last_sentence)
                }
                is TTSState.Stopped -> {
                    binding.currentPhraseTextView.text = getString(R.string.stopped_speech)
                    binding.buttonStop.isEnabled = false
                }
            }
        }
    }
}
