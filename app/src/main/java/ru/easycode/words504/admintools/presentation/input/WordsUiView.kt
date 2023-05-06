package ru.easycode.words504.admintools.presentation.input

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ru.easycode.words504.admintools.presentation.WordUi
import ru.easycode.words504.databinding.WordsUiLayoutBinding

class WordsUiView : LinearLayout, SaveAndReadWordUi, WordUi.Mapper<Unit> {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val binding: WordsUiLayoutBinding =
        WordsUiLayoutBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    override fun save(data: WordUi) = data.map(this)

    override fun read() = with(binding) {
        val text = indexInputEditText.text
        WordUi.Base(
            wordInputEditText.text.toString(),
            if (text.isNullOrEmpty()) Int.MIN_VALUE else text.toString().toInt(),
            dictionaryFormInputEditText.text.toString()
        )
    }

    override fun map(ui: String, index: Int, dictionaryForm: String) = with(binding) {
        wordInputEditText.setText(ui)
        indexInputEditText.setText(if (index == Int.MIN_VALUE) "" else index.toString())
        dictionaryFormInputEditText.setText(dictionaryForm)
    }
}
