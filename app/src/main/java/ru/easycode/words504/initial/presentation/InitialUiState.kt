package ru.easycode.words504.initial.presentation

import android.view.View

interface InitialUiState {

    object Loading : InitialUiState, ApplyProgress {
        override fun apply(view: View) {
            view.visibility = View.VISIBLE
        }
    }

    data class Error(private val message: String) : InitialUiState, ApplyError {
        override fun apply(view: ErrorView) {
            view.visibility = View.VISIBLE
            view.showError(message)
        }
    }

    interface ApplyError {
        fun apply(view: ErrorView)
    }

    interface ApplyProgress {
        fun apply(view: View)
    }
}
