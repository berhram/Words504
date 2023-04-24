package ru.easycode.words504.recognition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import ru.easycode.words504.R
import ru.easycode.words504.presentation.BaseFragment

class TestVoiceRecognitionFragment : BaseFragment<TestSTTViewModel>() {

    override val viewModelClass = TestSTTViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_test_voice_recognition,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val launcher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                viewModel.permissionCallback(isGranted)
            }

        viewModel.observeRequestPermission(this) {
            it.handle(this, launcher)
        }

        val textView = view.findViewById<TextView>(R.id.resultTextView)

        val button = view.findViewById<Button>(R.id.speakButton)
        viewModel.init(savedInstanceState == null)
        button.setOnClickListener {
            viewModel.startRecord()
        }
        viewModel.observeRequestPermission(this) {
            it.handle(this, launcher)
        }
        viewModel.observeRecognitionResult(this) {
            it.show(textView)
        }
    }
}
