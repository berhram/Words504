package ru.easycode.words504.tts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.words504.databinding.FragmentTtsTestBinding
import ru.easycode.words504.presentation.BaseFragment

class TTSTestFragment : BaseFragment<TTSTestFragmentViewModel.Base, FragmentTtsTestBinding>() {
    override val viewModelClass: Class<TTSTestFragmentViewModel.Base> =
        TTSTestFragmentViewModel.Base::class.java

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentTtsTestBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentUIState: TTSTestFragmentUIState? = null
        with(binding) {
            speakButton.setOnClickListener {
                viewModel.speak(textView.text.toString())
            }
            clearLogButton.setOnClickListener {
                logTextView.text = ""
            }
            controlButton.setOnClickListener {
                currentUIState?.let {
                    if (it is TTSTestFragmentUIState.Start || it is TTSTestFragmentUIState.Resume) {
                        viewModel.pause()
                    } else {
                        viewModel.resume()
                    }
                }
            }
        }
        viewModel.observe(this) {
            currentUIState = it
            it.map(binding.logTextView, binding.controlButton)
        }
    }
}
