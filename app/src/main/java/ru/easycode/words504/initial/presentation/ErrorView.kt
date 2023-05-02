package ru.easycode.words504.initial.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ru.easycode.words504.databinding.ErrorLayoutBinding

class ErrorView : LinearLayout, ShowError, RetryListener {
    private val binding: ErrorLayoutBinding =
        ErrorLayoutBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setRetryListener(listener: () -> Unit) =
        binding.retry.setOnClickListener { listener.invoke() }

    override fun showError(message: String) {
        binding.message.text = message
    }
}

interface ShowError {
    fun showError(message: String)
}

interface RetryListener {
    fun setRetryListener(listener: () -> Unit)
}
