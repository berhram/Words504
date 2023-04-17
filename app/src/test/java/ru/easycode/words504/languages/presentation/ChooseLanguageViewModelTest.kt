package ru.easycode.words504.languages.presentation

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.presentation.Communication

/**
 * @author Asatryan on 17.04.2023
 */
class ChooseLanguageViewModelTest {

    @Test
    fun `test initial`() {
        val dataSource = FakeLanguagesCacheDataSource()
        dataSource.save(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )
        val userTempChoice = FakeChosenLanguageCache()
        val chosenLanguage = FakeChosenLanguageCache()
        val communication = FakeCommunication()
        val saveAndRestore = FakeSaveAndRestore()

        val viewModel = ChooseLanguageViewModel(
            communication = communication,
            languagesCacheDataSource = dataSource,
            userChoice = userTempChoice,
            chosenLanguage = chosenLanguage
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            communication.state,
            ChooseLanguageState.Initial(
                languages = listOf<LanguageUi>(
                    LanguageUi.NotChosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )
    }

    @Test
    fun `test choose language`() {
        val dataSource = FakeLanguagesCacheDataSource()
        dataSource.save(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )
        val userTempChoice = FakeChosenLanguageCache()
        val chosenLanguage = FakeChosenLanguageCache()
        val communication = FakeCommunication()
        val saveAndRestore = FakeSaveAndRestore()

        val viewModel = ChooseLanguageViewModel(
            communication = communication,
            languagesCacheDataSource = dataSource,
            userChoice = userTempChoice,
            chosenLanguage = chosenLanguage
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            communication.state,
            ChooseLanguageState.Initial(
                languages = listOf<LanguageUi>(
                    LanguageUi.NotChosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )

        viewModel.choose(id = "ru")
        assertEquals(LanguageCache.Base("ru", "Russian"), userTempChoice.read())
        assertEquals(
            communication.state,
            ChooseLanguageState.Chosen(
                languages = listOf<LanguageUi>(
                    LanguageUi.Chosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )
    }

    @Test
    fun `test choose other language`() {
        val dataSource = FakeLanguagesCacheDataSource()
        dataSource.save(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )
        val userTempChoice = FakeChosenLanguageCache()
        val chosenLanguage = FakeChosenLanguageCache()
        val communication = FakeCommunication()
        val saveAndRestore = FakeSaveAndRestore()

        val viewModel = ChooseLanguageViewModel(
            communication = communication,
            languagesCacheDataSource = dataSource,
            userChoice = userTempChoice,
            chosenLanguage = chosenLanguage
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            communication.state,
            ChooseLanguageState.Initial(
                languages = listOf<LanguageUi>(
                    LanguageUi.NotChosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )

        viewModel.choose(id = "ru")
        assertEquals(LanguageCache.Base("ru", "Russian"), userTempChoice.read())
        assertEquals(
            communication.state,
            ChooseLanguageState.Chosen(
                languages = listOf<LanguageUi>(
                    LanguageUi.Chosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )

        viewModel.choose(id = "ch")
        assertEquals(LanguageCache.Base("ch", "Chinese"), userTempChoice.read())
        assertEquals(
            communication.state,
            ChooseLanguageState.Chosen(
                languages = listOf<LanguageUi>(
                    LanguageUi.NotChosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.Chosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )
    }

    @Test
    fun `test restore language`() {
        val dataSource = FakeLanguagesCacheDataSource()
        dataSource.save(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )
        val userTempChoice = FakeChosenLanguageCache()
        val chosenLanguage = FakeChosenLanguageCache()
        val communication = FakeCommunication()
        val saveAndRestore = FakeSaveAndRestore()

        val viewModel = ChooseLanguageViewModel(
            communication = communication,
            languagesCacheDataSource = dataSource,
            userChoice = userTempChoice,
            chosenLanguage = chosenLanguage
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            communication.state,
            ChooseLanguageState.Initial(
                languages = listOf<LanguageUi>(
                    LanguageUi.NotChosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )

        viewModel.choose(id = "ru")
        assertEquals(LanguageCache.Base("ru", "Russian"), userTempChoice.read())
        assertEquals(
            communication.state,
            ChooseLanguageState.Chosen(
                languages = listOf<LanguageUi>(
                    LanguageUi.Chosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )

        viewModel.save(saveAndRestore = saveAndRestore)
        assertEquals(LanguageCache.Base("ru", "Russian"), saveAndRestore.restore())

        val newDataSource = FakeLanguagesCacheDataSource()
        newDataSource.save(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )
        val newUserTempChoice = FakeChosenLanguageCache()
        val newChosenLanguage = FakeChosenLanguageCache()
        val newCommunication = FakeCommunication()

        val newViewModel = ChooseLanguageViewModel(
            communication = newCommunication,
            languagesCacheDataSource = newDataSource,
            userChoice = newUserTempChoice,
            chosenLanguage = newChosenLanguage
        )

        newViewModel.init(saveAndRestore = saveAndRestore)
        assertEquals(
            newCommunication.state,
            ChooseLanguageState.Chosen(
                languages = listOf<LanguageUi>(
                    LanguageUi.Chosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )
        assertEquals(LanguageCache.Base("ru", "Russian"), userTempChoice.read())
    }

    @Test
    fun `test save user choice`() {
        val dataSource = FakeLanguagesCacheDataSource()
        dataSource.save(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )
        val userTempChoice = FakeChosenLanguageCache()
        val chosenLanguage = FakeChosenLanguageCache()
        val communication = FakeCommunication()
        val saveAndRestore = FakeSaveAndRestore()

        val viewModel = ChooseLanguageViewModel(
            //todo navigation,
            communication = communication,
            languagesCacheDataSource = dataSource,
            userChoice = userTempChoice,
            chosenLanguage = chosenLanguage
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            communication.state,
            ChooseLanguageState.Initial(
                languages = listOf<LanguageUi>(
                    LanguageUi.NotChosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )

        viewModel.choose(id = "ru")
        assertEquals(LanguageCache.Base("ru", "Russian"), userTempChoice.read())
        assertEquals(
            communication.state,
            ChooseLanguageState.Chosen(
                languages = listOf<LanguageUi>(
                    LanguageUi.Chosen(
                        id = "ru",
                        value = "Russian"
                    ),
                    LanguageUi.NotChosen(
                        id = "ch",
                        value = "Chinese"
                    )
                )
            )
        )

        viewModel.save()
        assertEquals(LanguageCache.Base("ru", "Russian"), chosenLanguage.read())
        assertEquals(navigation.screen, MainScreen)
    }
}

private class FakeCommunication : Communication.Mutable<ChooseLanguageState> {
    var state: ChooseLanguageState? = null

    override fun map(source: ChooseLanguageState) {
        state = source
    }
}

private class FakeSaveAndRestore : SaveAndRestore<LanguageCache> {

    private var cache: LanguageCache = LanguageCache.Base("", "")

    override fun isEmpty(): Boolean {
        return cache.isEmpty()
    }

    override fun save(obj: LanguageCache) {
        cache = obj
    }

    override fun restore(): LanguageCache {
        return cache
    }
}

private class FakeChosenLanguageCache : ChosenLanguageCache.Mutable {

    private var languageCache: LanguageCache = LanguageCache.Base("", "")

    override fun read(): LanguageCache = languageCache

    override fun save(data: LanguageCache) {
        languageCache = data
    }
}

private class FakeLanguagesCacheDataSource : LanguagesCacheDataSource.Mutable {
    private val list = mutableListOf<LanguageCache>()

    override fun read(): List<LanguageCache> {
        return list
    }

    override fun save(data: List<LanguageCache>) {
        list.clear()
        list.addAll(data)
    }
}
