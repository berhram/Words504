package ru.easycode.words504.admintools.presentation.input

import android.text.Editable
import ru.easycode.words504.domain.Mapper

class WordsIndexesTextWatcher(
    private val inputLayout: Mapper.Unit<String>,
    private val output: Mapper<String, String>
) : EditTextWatcher() {

    override fun afterTextChanged(sequence: Editable) =
        inputLayout.map(output.map(sequence.toString()))
}
