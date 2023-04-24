package ru.easycode.words504.recognition.domain

import ru.easycode.words504.domain.HandleError
import ru.easycode.words504.recognition.data.STTErrorsFactory

class STTHandleError(
    private val toSTTUiError: ToSTTUiError,
    private val errorsFactory: STTErrorsFactory
) : HandleError<Int, String> {

    override fun handle(source: Int): String = toSTTUiError.map(errorsFactory.createError(source))
}
