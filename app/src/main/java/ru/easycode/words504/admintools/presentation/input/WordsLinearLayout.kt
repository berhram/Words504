package ru.easycode.words504.admintools.presentation.input

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.children
import ru.easycode.words504.admintools.presentation.WordUi

class WordsLinearLayout : LinearLayout, WordsUiContainer {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun save(data: List<WordUi>) = data.forEach {
        addView(WordsUiView(context).apply { save(it) })
    }

    override fun read() = children.map {
        (it as SaveAndReadWordUi).read()
    }.toList()

    override fun add() = addView(WordsUiView(context))
}
