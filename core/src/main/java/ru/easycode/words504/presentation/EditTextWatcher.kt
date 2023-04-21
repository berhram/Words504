package ru.easycode.words504.presentation

import android.text.Editable
import android.text.TextWatcher

abstract class EditTextWatcher : TextWatcher {
    override fun onTextChanged(sequence: CharSequence, start: Int, before: Int, count: Int) = Unit
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun afterTextChanged(sequence: Editable) = Unit
}
