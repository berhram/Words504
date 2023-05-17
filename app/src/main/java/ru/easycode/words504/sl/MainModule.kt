package ru.easycode.words504.sl

import ru.easycode.words504.MainViewModel

class MainModule(private val core: CoreModule) : Module<MainViewModel.Base> {
    override fun viewModel(): MainViewModel.Base = MainViewModel.Base(core.provideNavigation())
}
