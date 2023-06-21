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
        with(binding) {
            speakButton.setOnClickListener {
                viewModel.speak(textView.text.toString())
            }
            stopButton.setOnClickListener {

            }
            playButton.setOnClickListener {

            }
        }
    }
}
