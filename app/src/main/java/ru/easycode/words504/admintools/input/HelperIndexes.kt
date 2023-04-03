package ru.easycode.words504.admintools.input

import ru.easycode.words504.domain.Mapper

class HelperIndexes(private val helper: (output: CharSequence) -> Unit) : Mapper.Unit<String> {

    override fun map(source: String) = helper.invoke(source)
}
