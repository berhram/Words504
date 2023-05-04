package ru.easycode.words504.initial.domain

import ru.easycode.words504.R
import ru.easycode.words504.domain.HandleError
import ru.easycode.words504.presentation.ManageResources

class HandleUiError(private val manageResources: ManageResources) : HandleError<Throwable, String> {
    override fun handle(source: Throwable): String {
        return source.message ?: manageResources.string(R.string.error)
    }
}
