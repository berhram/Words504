package ru.easycode.words504.admintools.presentation.input

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ru.easycode.words504.admintools.presentation.SaveAndReadWordUi
import ru.easycode.words504.admintools.presentation.WordUi
import ru.easycode.words504.databinding.WordsUiLayoutBinding

class WordUiView : LinearLayout, SaveAndReadWordUi, WordUi.Mapper<Unit> {
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

    override fun save(data: WordUi) {
        val defaultIndex = "0"
        binding.apply {
            if (inputIndexEditText.text.toString() == "") {
                inputIndexEditText.setText(defaultIndex)
            }
        }

        data.map(this)
    }

    override fun read() = with(binding) {
        val defaultIndex = "0"
        binding.apply {
            if (inputIndexEditText.text.toString() == "") {
                inputIndexEditText.setText(defaultIndex)
            }
        }
        WordUi.Base(
            inputWordEditText.text.toString(),
            inputIndexEditText.text.toString().toInt(),
            inputDictionaryFormEditText.text.toString()
        )
    }

    override fun map(ui: String, index: Int, dictionaryForm: String) {
        binding.apply {
            inputWordEditText.setText(ui)
            inputIndexEditText.setText(index.toString())
            inputDictionaryFormEditText.setText(dictionaryForm)
        }
    }
}
