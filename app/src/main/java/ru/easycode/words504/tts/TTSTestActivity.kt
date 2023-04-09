package ru.easycode.words504.tts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.words504.databinding.ActivityTtsBinding

class TTSTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTtsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTtsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ttsEngine = TTSEngine.Base(this) {
            binding.buttonSpeak.isEnabled = true
            binding.buttonSpeakBySentences.isEnabled = true
        }

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

        binding.buttonSpeak.setOnClickListener {
            binding.buttonStop.isEnabled = true
            ttsEngine.speak(binding.textInputEditText.text.toString())
        }

        binding.buttonSpeakBySentences.setOnClickListener {
            binding.buttonStop.isEnabled = true
            ttsEngine.speak(binding.textInputEditText.text.toString().split("[.!?]".toRegex()))
        }

        binding.buttonStop.setOnClickListener {
            ttsEngine.stop()
        }
    }
}