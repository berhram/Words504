package ru.easycode.words504.recognition.domain

import ru.easycode.words504.domain.HandleError
import ru.easycode.words504.recognition.data.STTErrors

class STTHandleError(private val toSTTUiError: ToSTTUiError) : HandleError<Int, String> {

    override fun handle(source: Int): String = toSTTUiError.map(STTErrors.state(source))
}
