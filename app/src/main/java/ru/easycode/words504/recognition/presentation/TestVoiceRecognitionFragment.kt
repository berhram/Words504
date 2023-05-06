package ru.easycode.words504.recognition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import ru.easycode.words504.databinding.FragmentTestVoiceRecognitionBinding
import ru.easycode.words504.presentation.BaseFragment

class TestVoiceRecognitionFragment :
    BaseFragment<TestSTTViewModel, FragmentTestVoiceRecognitionBinding>() {

    override val viewModelClass = TestSTTViewModel::class.java

    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTestVoiceRecognitionBinding =
        FragmentTestVoiceRecognitionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val launcher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                viewModel.permissionCallback(isGranted)
            }

        viewModel.observeRequestPermission(this) {
            it.handle(this, launcher)
        }

        viewModel.init(savedInstanceState == null)
        binding.speakButton.setOnClickListener {
            viewModel.startRecord()
        }
        viewModel.observeRequestPermission(this) {
            it.handle(this, launcher)
        }
        viewModel.observeRecognitionResult(this) {
            it.show(binding.resultTextView)
        }
    }
}
