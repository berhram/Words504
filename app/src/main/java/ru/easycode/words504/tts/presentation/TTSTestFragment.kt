package ru.easycode.words504.tts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import ru.easycode.words504.databinding.FragmentTtsBinding
import ru.easycode.words504.presentation.BaseFragment

abstract class TTSBaseFragment<V : ViewBinding> : BaseFragment<TTSViewModel.Base, V>() {

    class TTSTestFragment : TTSBaseFragment<FragmentTtsBinding>() {

        override val viewModelClass: Class<TTSViewModel.Base> = TTSViewModel.Base::class.java
        
        override fun fragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
        ): FragmentTtsBinding = FragmentTtsBinding.inflate(inflater, container, false)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding.buttonSpeak.setOnClickListener {
                viewModel.speak(binding.textInputEditText.text.toString().split(" "))
            }
            binding.buttonSpeakAsOne.setOnClickListener {
                viewModel.speak(listOf(binding.textInputEditText.text.toString()))
            }
        }
    }
}
