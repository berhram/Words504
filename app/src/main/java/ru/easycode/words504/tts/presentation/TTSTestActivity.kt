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
        if (savedInstanceState == null) {
            viewModel.navigate(TTSTestScreen)
        }
        viewModel.init {}

        viewModel.observe(this) {
            it.navigate(supportFragmentManager, binding.ttsContainer.id)
        }
        viewModel.observeTts(this) {
            viewModel.speak(it)
        }
        viewModel.observeTTSControl(this) {
            viewModel.changeState(it)
        }
    }
}
