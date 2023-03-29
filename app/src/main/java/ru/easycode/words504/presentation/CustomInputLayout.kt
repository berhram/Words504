package ru.easycode.words504.presentation

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import ru.easycode.words504.core.presentation.custom.ShowView
import ru.easycode.words504.presentation.custom.CustomEditTextWatcher

class CustomInputLayout : TextInputLayout, ShowView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val simpleWatcher = CustomEditTextWatcher(this)

    override fun show() {
        this.editText!!.addTextChangedListener(simpleWatcher)
    }
}
