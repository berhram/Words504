package ru.easycode.words504.initial.presentation

interface InitialUiState {

    object Loading : InitialUiState

    data class Error(private val message: String) : InitialUiState
}