package ru.easycode.words504.admintools.input

import android.text.Editable
import ru.easycode.words504.domain.Mapper

class WordsIndexesTextWatcher(
    private val helper: Mapper.Unit<String>,
    private val output: Mapper<String, String>
) : EditTextWatcher() {

    override fun afterTextChanged(sequence: Editable) = helper.map(output.map(sequence.toString()))
}
