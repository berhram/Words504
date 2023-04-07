package ru.easycode.words504.recognition.domain

import ru.easycode.words504.domain.HandleError
import ru.easycode.words504.domain.Mapper
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.recognition.data.STTErrors

class STTHandleError : HandleError<Int, STTErrors> {

    override fun handle(source: Int): STTErrors = STTErrors.state(source)
}

class ToSttUiError(
    private val manageResources: ManageResources
) : Mapper<STTErrors, String> {

    override fun map(source: STTErrors) = source.string(manageResources)

}