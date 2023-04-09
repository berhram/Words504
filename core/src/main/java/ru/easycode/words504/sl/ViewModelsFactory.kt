package ru.easycode.words504.sl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelsFactory(
    private val dependencyContainer: DependencyContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        dependencyContainer.module(modelClass) as T
}
