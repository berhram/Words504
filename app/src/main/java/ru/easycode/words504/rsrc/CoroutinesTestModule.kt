package ru.easycode.words504.rsrc

import ru.easycode.words504.data.cloud.ProvideConverterFactory
import ru.easycode.words504.data.cloud.ProvideLoggingInterceptor
import ru.easycode.words504.data.cloud.ProvideOkHttpClientBuilder
import ru.easycode.words504.data.cloud.ProvideRetrofitBuilder
import ru.easycode.words504.languages.data.cloud.AuthHeaderInterceptorProvider
import ru.easycode.words504.languages.data.cloud.LanguagesMakeService
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.rsrc.adapter.TranslateResultUi
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module
import ru.easycode.words504.translate.data.cloud.TranslateService

class CoroutinesTestModule(private val core: CoreModule) : Module<TestTranslateViewModel> {

    private val uiCommunication: Communication.Mutable<TranslateResultUi> by lazy {
        TestTranslateCommunication()
    }

    override fun viewModel(): TestTranslateViewModel = TestTranslateViewModel(
        service = LanguagesMakeService(
            retrofitBuilder = ProvideRetrofitBuilder.Base(
                httpClientBuilder = ProvideOkHttpClientBuilder.AddInterceptor(
                    ProvideLoggingInterceptor.Debug(),
                    ProvideOkHttpClientBuilder.AddInterceptor(
                        AuthHeaderInterceptorProvider(),
                        ProvideOkHttpClientBuilder.Base()
                    )
                ),
                provideConverterFactory = ProvideConverterFactory.Base()
            )
        ).service(TranslateService::class.java),
        dispatchers = core.provideDispatchers(),
        uiCommunication = uiCommunication
    )
}
