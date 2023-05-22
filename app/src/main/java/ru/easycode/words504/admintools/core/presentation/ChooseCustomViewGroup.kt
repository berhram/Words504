package ru.easycode.words504.admintools.core.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CompoundButton
import android.widget.FrameLayout
import androidx.core.view.children
import com.google.android.material.chip.Chip
import ru.easycode.words504.R

/**
 * @author Asatryan on 22.05.2023
 */
class ChooseCustomViewGroup : FrameLayout, ChooseView {

    private val chipGroup: ViewGroup

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        inflate(context, R.layout.choose_custom_view_group, this)
        chipGroup = findViewById(R.id.chipGroup)
    }

    override fun init(list: List<Choose>, onClick: (String) -> Unit) = list.forEach { choose ->
        val chip = Chip(context)
        chip.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        choose.apply(chip) {
            chipGroup.children.find { (it as CompoundButton).isSelected }?.let {
                it.isSelected = false
            }
            onClick.invoke(choose.id())
        }
        chip.text = choose.text()
        chipGroup.addView(chip)
    }
}

interface ChooseView {

    fun init(list: List<Choose>, onClick: (String) -> Unit)
}

interface Choose {

    fun text(): String
    fun id(): String
    fun apply(chip: CompoundButton, onClick: (String) -> Unit)

    data class Base(
        private val uniqueId: String,
        private val text: String
    ) : Choose {

        override fun text() = text
        override fun id() = uniqueId

        override fun apply(chip: CompoundButton, onClick: (String) -> Unit) = with(chip) {
            setOnClickListener {
                onClick.invoke(uniqueId)
                isSelected = true
            }
        }
    }
}
