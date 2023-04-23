package ru.easycode.words504.presentation

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class CorrectTextInputEditText : TextInputEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        setSelection(text.toString().length)
    }
}
