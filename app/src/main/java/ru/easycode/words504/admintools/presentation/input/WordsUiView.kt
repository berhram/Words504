package ru.easycode.words504.admintools.presentation.input

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ru.easycode.words504.R
import ru.easycode.words504.admintools.presentation.SaveAndReadWordUi
import ru.easycode.words504.admintools.presentation.WordUi
import ru.easycode.words504.presentation.CorrectTextInputEditText

class WordUiView : LinearLayout, SaveAndReadWordUi, WordUi.Mapper<Unit> {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val inputWord: CorrectTextInputEditText
    private val inputIndex: CorrectTextInputEditText
    private val inputDictionaryForm: CorrectTextInputEditText

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.words_ui_layout, this, true)
        inputWord = findViewById(R.id.inputWord_editText)
        inputIndex = findViewById(R.id.inputIndex_editText)
        inputDictionaryForm = findViewById(R.id.inputDictionaryForm_editText) //todo viewbinning
    }

    override fun save(data: WordUi) {
        data.map(this)

    }

    override fun read() = WordUi.Base(
        inputWord.text.toString(),
        inputIndex.text.toString().toInt(),
        inputDictionaryForm.text.toString()
    )

    override fun map(ui: String, index: Int, dictionaryForm: String) {
        inputWord.setText(ui)
        inputIndex.setText(index.toString())
        inputDictionaryForm.setText(dictionaryForm)
    }
}

