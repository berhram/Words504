package ru.easycode.words504.tts.presentation

import android.widget.Button
import android.widget.TextView
import ru.easycode.words504.R
import ru.easycode.words504.presentation.ManageResources

interface TTSTestFragmentUIState {
    fun map(textView: TextView, controlButton: Button)

    abstract class Abstract(
        private val text: String
    ) : TTSTestFragmentUIState {
        protected abstract val stateName: String
        override fun map(textView: TextView, controlButton: Button) {
            val oldText = textView.text
            "$oldText \n$stateName -> $text".also { textView.text = it }
        }
    }

    class Start(
        text: String,
        private val manageResources: ManageResources
    ) : Abstract(text) {
        override val stateName: String = manageResources.string(R.string.started)

        override fun map(textView: TextView, controlButton: Button) {
            super.map(textView, controlButton)
            controlButton.text = manageResources.string(R.string.pause)
        }
    }

    class Finish(text: String, manageResources: ManageResources) : Abstract(text) {
        override val stateName: String = manageResources.string(R.string.finished)
    }

    class Pause(
        text: String,
        private val manageResources: ManageResources
    ) : Abstract(text) {
        override val stateName: String = manageResources.string(R.string.pause)

        override fun map(textView: TextView, controlButton: Button) {
            super.map(textView, controlButton)
            controlButton.text = manageResources.string(R.string.resume)
        }
    }

    class Resume(
        text: String,
        private val manageResources: ManageResources
    ) : Abstract(text) {
        override val stateName: String = manageResources.string(R.string.resume)

        override fun map(textView: TextView, controlButton: Button) {
            super.map(textView, controlButton)
            controlButton.text = manageResources.string(R.string.pause)
        }
    }

    class Error(message: String, manageResources: ManageResources) : Abstract(message) {
        override val stateName: String = manageResources.string(R.string.error)
    }
}
