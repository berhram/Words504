package ru.easycode.words504.admintools

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import ru.easycode.words504.admintools.input.ApostropheMatch
import ru.easycode.words504.admintools.input.HandleIndexes
import ru.easycode.words504.admintools.input.HelperIndexes
import ru.easycode.words504.admintools.input.LetterMatch
import ru.easycode.words504.admintools.input.WordsIndexesTextWatcher

class WordsIndexesInputLayout : TextInputLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val helper = HelperIndexes { output -> helperText = output }
    private val handle = HandleIndexes(LetterMatch(), ApostropheMatch())
    private val simpleWatcher = WordsIndexesTextWatcher(helper = helper, handle)

    init {
        editText?.addTextChangedListener(simpleWatcher)
    }
}
