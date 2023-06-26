package ru.easycode.words504.tts.presentation

import android.widget.TextView
import ru.easycode.words504.R
import ru.easycode.words504.presentation.ManageResources

interface TTSTestFragmentUIState {
    fun map(textView: TextView)

    abstract class Abstract(
        private val text: String
    ) : TTSTestFragmentUIState {
        protected abstract val prefix: String
        override fun map(textView: TextView) {
            val oldText = textView.text
            "$oldText \n$prefix -> $text".also { textView.text = it }
        }
    }

    class Start(text: String, manageResources: ManageResources) : Abstract(text) {
        override val prefix: String = manageResources.string(R.string.started)
    }

    class Finish(text: String, manageResources: ManageResources) : Abstract(text) {
        override val prefix: String = manageResources.string(R.string.finished)
    }

    class Pause(text: String, manageResources: ManageResources) : Abstract(text) {
        override val prefix: String = manageResources.string(R.string.pause)
    }

    class Resume(text: String, manageResources: ManageResources) : Abstract(text) {
        override val prefix: String = manageResources.string(R.string.resume)
    }

    class Stop(text: String, manageResources: ManageResources) : Abstract(text) {
        override val prefix: String = manageResources.string(R.string.stop)
    }

    class Error(message: String, manageResources: ManageResources) : Abstract(message) {
        override val prefix: String = manageResources.string(R.string.error)
    }
}
