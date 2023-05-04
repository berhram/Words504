package ru.easycode.words504.initial.sl

import ru.easycode.words504.data.cloud.ProvideConverterFactory
import ru.easycode.words504.data.cloud.ProvideOkHttpClientBuilder
import ru.easycode.words504.data.cloud.ProvideRetrofitBuilder
import ru.easycode.words504.initial.data.BaseInitialRepository
import ru.easycode.words504.initial.domain.HandleUiError
import ru.easycode.words504.initial.domain.InitialInteractor
import ru.easycode.words504.initial.presentation.InitialCommunication
import ru.easycode.words504.initial.presentation.InitialViewModel
import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.languages.data.cloud.AuthHeaderInterceptorProvider
import ru.easycode.words504.languages.data.cloud.LanguageCloudCacheMapper
import ru.easycode.words504.languages.data.cloud.LanguagesCloudDataSource
import ru.easycode.words504.languages.data.cloud.LanguagesMakeService
import ru.easycode.words504.languages.data.cloud.LanguagesService
import ru.easycode.words504.languages.domain.HandleDomainError
import ru.easycode.words504.languages.domain.HandleHttpError
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class InitialModule(private val core: CoreModule) : Module<InitialViewModel> {

    override fun viewModel(): InitialViewModel {
        val objectStorage = core.provideObjectStorage()
        val service = LanguagesMakeService(
            retrofitBuilder = ProvideRetrofitBuilder.Base(
                httpClientBuilder = ProvideOkHttpClientBuilder.AddInterceptor(
                    AuthHeaderInterceptorProvider(),
                    core.provideHttpClientBuilder()
                ),
                provideConverterFactory = ProvideConverterFactory.Base()
            )
        ).service(LanguagesService::class.java)

        val repository = BaseInitialRepository(
            chosenCache = ChosenLanguageCache.Base(objectStorage),
            languagesCache = LanguagesCacheDataSource.Base(objectStorage),
            languagesCloud = LanguagesCloudDataSource.Base(
                service,
                HandleDomainError(HandleHttpError())
            ),
            languageMapper = LanguageCloudCacheMapper()
        )

        return InitialViewModel(
            interactor = InitialInteractor.Base(repository, HandleUiError(core)),
            communication = InitialCommunication.Base(),
            navigation = core.provideNavigation(),
            dispatchers = core.provideDispatchers()
        )
    }
}
