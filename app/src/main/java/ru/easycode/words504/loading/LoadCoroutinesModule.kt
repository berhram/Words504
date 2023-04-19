package ru.easycode.words504.loading

import ru.easycode.words504.languages.data.cloud.LanguagesMakeService
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module
import ru.easycode.words504.translate.data.cloud.TranslateService

class LoadCoroutinesModule(private val core: CoreModule) : Module<LoadTranslateViewModel> {

    override fun viewModel(): LoadTranslateViewModel = LoadTranslateViewModel(
        service = LanguagesMakeService(retrofitBuilder = core.provideRetrofit())
            .service(TranslateService::class.java),
        dispatchers = core.provideDispatchers()
    )
}
