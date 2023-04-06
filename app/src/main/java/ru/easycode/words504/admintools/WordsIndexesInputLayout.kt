package ru.easycode.words504.admintools

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.textfield.TextInputLayout
import ru.easycode.words504.R
import ru.easycode.words504.admintools.input.ApostropheMatch
import ru.easycode.words504.admintools.input.HandleIndexes
import ru.easycode.words504.admintools.input.LetterMatch
import ru.easycode.words504.admintools.input.WordsIndexesTextWatcher
import ru.easycode.words504.domain.Mapper

class WordsIndexesInputLayout : TextInputLayout, Mapper.Unit<String> {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val handle = HandleIndexes(LetterMatch(), ApostropheMatch())
    private val simpleWatcher = WordsIndexesTextWatcher(this, handle)

    init {
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.words_indexes_input_layout, this, true)
        editText?.addTextChangedListener(simpleWatcher)
    }

    override fun map(source: String) {
        helperText = source
    }
}
