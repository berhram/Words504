package ru.easycode.words504.sl

import ru.easycode.words504.MainViewModel

class MainModule : Module<MainViewModel> {
    override fun viewModel(): MainViewModel = MainViewModel()
}
