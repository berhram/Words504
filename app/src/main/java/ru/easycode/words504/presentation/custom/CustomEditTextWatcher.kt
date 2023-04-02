package ru.easycode.words504.presentation.custom

import android.text.Editable
import com.google.android.material.textfield.TextInputLayout

class CustomEditTextWatcher(private val editTextLayout: TextInputLayout) : EditTextWatcher() {

    override fun afterTextChanged(sequence: Editable) {
        val indexes = HandleIndexes()
        editTextLayout.helperText = indexes.map(sequence.toString())
    }
}
