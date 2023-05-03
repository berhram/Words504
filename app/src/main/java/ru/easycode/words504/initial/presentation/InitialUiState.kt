package ru.easycode.words504.initial.presentation

import android.view.View

interface InitialUiState {

    fun apply(error: ErrorView, progress: View)

    object Loading : InitialUiState {

        override fun apply(error: ErrorView, progress: View) {
            error.visibility = View.INVISIBLE
            progress.visibility = View.VISIBLE
        }
    }

    data class Error(private val message: String) : InitialUiState {

        override fun apply(error: ErrorView, progress: View) {
            progress.visibility = View.INVISIBLE
            error.visibility = View.VISIBLE
            error.showError(message)
        }
    }
}
