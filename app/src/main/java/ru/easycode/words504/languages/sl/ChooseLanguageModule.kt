package ru.easycode.words504.languages.sl

import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.languages.data.repository.ChooseLanguageRepository
import ru.easycode.words504.languages.presentation.ChooseLanguageCommunication
import ru.easycode.words504.languages.presentation.ChooseLanguageViewModel
import ru.easycode.words504.languages.presentation.LanguageUiChosenMapper
import ru.easycode.words504.languages.presentation.LanguageUiMapper
import ru.easycode.words504.sl.CoreModule
import ru.easycode.words504.sl.Module

class ChooseLanguageModule(
    private val core: CoreModule
) : Module<ChooseLanguageViewModel.Base> {

    override fun viewModel(): ChooseLanguageViewModel.Base {

        val objectStorage = core.provideObjectStorage()

        val languagesCacheDataSource = LanguagesCacheDataSource.Base(objectStorage)
        val userChoiceTemporary = ChosenLanguageCache.Temporary()

        val repository = ChooseLanguageRepository.Base(
            languagesCacheDataSource = languagesCacheDataSource,
            userChoice = userChoiceTemporary,
            chosenLanguage = ChosenLanguageCache.Base(objectStorage)
        )
        val mapper = LanguageUiChosenMapper(repository, LanguageUiMapper())

        return ChooseLanguageViewModel.Base(
            communication = ChooseLanguageCommunication.Base(),
            repository = repository,
            navigation = core.provideNavigation(),
            mapper = mapper
        )
    }
}
