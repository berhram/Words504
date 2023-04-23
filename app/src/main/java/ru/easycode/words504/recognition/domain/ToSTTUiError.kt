package ru.easycode.words504.recognition.domain

import ru.easycode.words504.domain.Mapper
import ru.easycode.words504.presentation.ManageResources
import ru.easycode.words504.recognition.data.STTError

class ToSTTUiError(
    private val manageResources: ManageResources
) : Mapper<STTError, String> {

    override fun map(source: STTError) = source.string(manageResources)
}
