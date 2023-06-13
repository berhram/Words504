package ru.easycode.words504.tts.presentation

import android.os.Bundle
import ru.easycode.words504.presentation.BaseActivity
import ru.easycode.words504.tts.data.TTSObserver

class TTSCoreActivity : BaseActivity<TTSViewModel.Base>(), TTSObserver {

    override val viewModelClass: Class<TTSViewModel.Base> = TTSViewModel.Base::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init {  }
        viewModel.observe(this) { it: List<String> ->
            viewModel.speak(it)
        }
    }
}