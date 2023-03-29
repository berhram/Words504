package ru.easycode.words504.presentation.custom

import android.text.Editable
import com.google.android.material.textfield.TextInputLayout

class CustomEditTextWatcher(private val editTextLayout: TextInputLayout) : EditTextWatcher() {

    private var indexesOutput = INITIAL_STRING
    private var previousLength = INITIAL_LENGTH
    private var previousSymbol = EMPTY

    override fun afterTextChanged(sequence: Editable) {
        val inputState = InputState.Base(sequence, previousLength, previousSymbol)
        val add = InputCondition.Add(sequence, inputState, Mapper.Add())
        val remove = InputCondition.Remove(sequence, inputState, Mapper.Remove())
        val empty = InputCondition.Empty(sequence)
        val started = InputCondition.Start(sequence)
        val conditions = listOf(empty, started, add, remove)

        indexesOutput = HelperLineState.Base(indexesOutput).show(conditions)
        previousLength = sequence.length
        previousSymbol = if (sequence.isNotEmpty()) sequence.last() else EMPTY

        editTextLayout.helperText = indexesOutput
    }

    companion object {
        private const val INITIAL_STRING = ""
        private const val INITIAL_LENGTH = 0
        private const val EMPTY = ' '
    }
}
