package ru.easycode.words504.sl

import ru.easycode.words504.data.cloud.ProvideConverterFactory
import ru.easycode.words504.data.cloud.ProvideLoggingInterceptor
import ru.easycode.words504.data.cloud.ProvideOkHttpClientBuilder
import ru.easycode.words504.data.cloud.ProvideRetrofitBuilder
import ru.easycode.words504.languages.data.cloud.AuthHeaderInterceptorProvider
import ru.easycode.words504.languages.data.cloud.LanguagesMakeService
import ru.easycode.words504.languages.data.cloud.TranslateService
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.rsrc.TestTranslateViewModel
import ru.easycode.words504.rsrc.TestTranslateCommunications
import ru.easycode.words504.rsrc.adapter.TranslateResultUi

class TestCoroutinesModule(private val core: CoreModule) : Module<TestTranslateViewModel> {

    private val uiCommunication: Communication.Mutable<TranslateResultUi> by lazy {
        TestTranslateCommunications()
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
