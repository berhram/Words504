package ru.easycode.words504.tts.presentation

import android.os.Bundle
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
//        val ttsEngine = TTSEngine.Base(this)
        viewModel.init {
            binding.buttonSpeak.isEnabled = true
            binding.buttonSpeakBySentences.isEnabled = true
        }

        binding.buttonSpeak.setOnClickListener {
            binding.buttonStop.isEnabled = true
            viewModel.ttsString(binding.textInputEditText.text.toString())
        }
        // TODO: почистить код
/*
        ttsEngine.setEndOfSpeechListener {
            runOnUiThread {
                binding.buttonStop.isEnabled = false
                binding.currentPhraseTextView.text = "Текст кончился"
                binding.previousPhraseTextView.text = "Кончилось произношение: ${it}"
            }
        }

        ttsEngine.setPartialEndOfSpeechListener {
            runOnUiThread {
                binding.previousPhraseTextView.text = "Кончилось произношение: ${it}"
            }
        }

        ttsEngine.setStartOfSpeechListener {
            runOnUiThread {
                binding.currentPhraseTextView.text = "Текущее предложение: ${it}"
            }
        }
 */

        binding.buttonSpeak.setOnClickListener {
            viewModel.ttsString(binding.textInputEditText.text.toString())
            binding.buttonStop.isEnabled = true
//            ttsEngine.speak(binding.textInputEditText.text.toString())
        }

        binding.buttonSpeakBySentences.setOnClickListener {
            viewModel.ttsStringList(binding.textInputEditText.text.toString().split("[.!?]".toRegex()))
            binding.buttonStop.isEnabled = true
//            ttsEngine.speak(binding.textInputEditText.text.toString().split("[.!?]".toRegex()))
        }

        binding.buttonStop.setOnClickListener {
            viewModel.stop()
//            ttsEngine.stop()
        }

        //todo перенести логику отсюда
        viewModel.observeTTSResult(this) {
            when(it) {
                is TTSState.Started -> {
                    binding.buttonStop.isEnabled = true
                    it.show(binding.currentPhraseTextView)
                    binding.currentTitle.text = "current text:"
                }
                is TTSState.Finished -> {
                    binding.currentPhraseTextView.text = "end of text."
                    binding.buttonStop.isEnabled = false
                }
                is TTSState.PartialFinished -> {
                    it.show(binding.previousPhraseTextView)
                    binding.previousTitle.text = "last part:"
                }
                is TTSState.Stopped -> {
                    binding.currentPhraseTextView.text = "speech was stopped"
                    binding.buttonStop.isEnabled = false
                }
            }
        }
    }

    // TODO: переживать поворот экрана. 
//    override fun onPause() {
//        viewModel.stop()
//        super.onPause()
//    }
//    override fun onDestroy() {
//        viewModel.shutdownTTS()
//        super.onDestroy()
//    }
}
