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

    override fun addRetryListener(listener: () -> Unit) =
        binding.retryButton.setOnClickListener { listener.invoke() }

    override fun showError(message: String) {
        binding.messageTextView.text = message
    }
}

interface ShowError {
    fun showError(message: String)
}

interface RetryListener {
    fun addRetryListener(listener: () -> Unit)
}
