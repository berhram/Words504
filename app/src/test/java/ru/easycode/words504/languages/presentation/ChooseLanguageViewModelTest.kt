package ru.easycode.words504.languages.presentation

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.MainScreen
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.repository.ChooseLanguageRepository
import ru.easycode.words504.presentation.Communication
import ru.easycode.words504.presentation.NavigationCommunication
import ru.easycode.words504.presentation.SaveAndRestore
import ru.easycode.words504.presentation.Screen

class ChooseLanguageViewModelTest {

    private lateinit var repository: FakeChooseLanguageRepository
    private lateinit var communication: FakeCommunication
    private lateinit var navigation: FakeNavigation
    private lateinit var viewModel: ChooseLanguageViewModel
    private lateinit var mapper: LanguageCache.Mapper<List<LanguageUi>>
    private val saveAndRestore: FakeSaveAndRestore = FakeSaveAndRestore()

    @Before
    fun setUp() {
        communication = FakeCommunication.Base()
        navigation = FakeNavigation.Base()
        repository = FakeChooseLanguageRepository.Base()
        mapper = LanguageUiChosenMapper(repository, LanguageUiMapper())

        viewModel = ChooseLanguageViewModel.Base(
            communication = communication,
            repository = repository,
            navigation = navigation,
            mapper = mapper
        )
    }

    @Test
    fun `test initial`() {
        repository.expectedLanguages(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            true,
            communication.same(
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
        )
    }

    @Test
    fun `test choose language`() {
        repository.expectedLanguages(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            true,
            communication.same(
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
        )

        viewModel.choose(id = "ru")
        assertEquals(
            true,
            communication.same(
                ChooseLanguageState.Chosen(
                    languages = listOf(
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
        )
    }

    @Test
    fun `test choose other language`() {
        repository.expectedLanguages(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            true,
            communication.same(
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
        )

        viewModel.choose(id = "ru")
        assertEquals(
            true,
            communication.same(
                ChooseLanguageState.Chosen(
                    languages = listOf(
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
        )

        viewModel.choose(id = "ch")
        assertEquals(
            true,
            communication.same(
                ChooseLanguageState.Chosen(
                    languages = listOf(
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
        )
    }

    @Test
    fun `test restore language`() {
        repository.expectedLanguages(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            true,
            communication.same(
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
        )

        viewModel.choose(id = "ru")
        assertEquals(
            true,
            communication.same(
                ChooseLanguageState.Chosen(
                    languages = listOf(
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
        )

        viewModel.save(saveAndRestore = saveAndRestore)
        assertEquals(LanguageCache.Base("ru", "Russian"), saveAndRestore.restore())

        val newRepository = FakeChooseLanguageRepository.Base()
        newRepository.expectedLanguages(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )

        val newCommunication = FakeCommunication.Base()
        val newNavigation = FakeNavigation.Base()
        val newViewModel = ChooseLanguageViewModel.Base(
            communication = newCommunication,
            repository = newRepository,
            navigation = newNavigation,
            mapper = mapper
        )

        newViewModel.init(saveAndRestore = saveAndRestore)
        assertEquals(
            true,
            newCommunication.same(
                ChooseLanguageState.Chosen(
                    languages = listOf(
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
        )
    }

    @Test
    fun `test save user choice`() {
        repository.expectedLanguages(
            listOf(
                LanguageCache.Base("ru", "Russian"),
                LanguageCache.Base("ch", "Chinese")
            )
        )

        viewModel.init(saveAndRestore = saveAndRestore)

        assertEquals(
            true,
            communication.same(
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
        )

        viewModel.choose(id = "ru")
        assertEquals(
            true,
            communication.same(
                ChooseLanguageState.Chosen(
                    languages = listOf(
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
        )

        viewModel.save()
        assertEquals(LanguageCache.Base("ru", "Russian"), repository.userChoice())
        navigation.same(MainScreen)
    }
}

private interface FakeCommunication : Communication.Mutable<ChooseLanguageState> {

    fun same(other: ChooseLanguageState): Boolean

    class Base : FakeCommunication {
        private var state: ChooseLanguageState? = null

        override fun same(other: ChooseLanguageState): Boolean = state == other

        override fun map(source: ChooseLanguageState) {
            state = source
        }
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

private interface FakeNavigation : NavigationCommunication.Update {

    fun same(other: Screen)

    class Base : FakeNavigation {
        private var screen: Screen? = null

        override fun same(other: Screen) {
            assertEquals(screen, other)
        }

        override fun map(source: Screen) {
            screen = source
        }
    }
}

private interface FakeChooseLanguageRepository : ChooseLanguageRepository {

    fun expectedLanguages(data: List<LanguageCache>)

    class Base : FakeChooseLanguageRepository {

        private var languages = emptyList<LanguageCache>()
        private var userChoice: LanguageCache = LanguageCache.Base("", "")
        private var chosen: LanguageCache = LanguageCache.Base("", "")

        override fun expectedLanguages(data: List<LanguageCache>) {
            languages = data
        }

        override fun languages(): List<LanguageCache> = languages

        override fun saveUserChoice(languageCache: LanguageCache) {
            userChoice = languageCache
        }

        override fun userChoice(): LanguageCache = userChoice

        override fun save() {
            chosen = userChoice
        }
    }
}
