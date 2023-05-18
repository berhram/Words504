package ru.easycode.words504.admintools.lessonslist.presentation

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.admintools.lessonslist.data.LessonCache
import ru.easycode.words504.admintools.lessonslist.domain.LessonsListRepository

class AdminLessonsListViewModelTest : BaseTest() {

    private lateinit var repository: FakeRepository
    private lateinit var viewModel: AdminLessonsListViewModel
    private lateinit var mapper: FakeLessonsUiMapper
    private lateinit var communication: FakeLessonsListCommunication

    @Before
    fun setUp() {
        repository = FakeRepository.Base(functionsCallsStack)
        communication = FakeLessonsListCommunication.Base(functionsCallsStack)
        mapper = FakeLessonsUiMapper()
        viewModel = AdminLessonsListViewModel.Base(
            repository,
            communication,
            mapper,
            fakeNavigation,
            TestDispatchersList()
        )
    }

    @Test
    fun `test initial`() = runBlocking {
        repository.changeExpected(
            listOf(
                FakeLessonCache(1, true),
                FakeLessonCache(2, false)
            )
        )

        viewModel.init()
        communication.same(LessonsUi.Loading)
        repository.checkCalledLessons()
        communication.same(
            LessonsUi.Success(
                listOf(
                    LessonUi(1, LessonState.Complete),
                    LessonUi(2, LessonState.InWork)
                )
            )
        )
        functionsCallsStack.checkStack(3)
    }

    @Test
    fun `test navigate to lesson details`() {
        // TODO
    }

    private interface FakeRepository : LessonsListRepository {
        fun changeExpected(expected: List<LessonCache>)
        fun checkCalledLessons()

        class Base(private val functionCallsStack: FunctionsCallsStack) : FakeRepository {
            private var data = emptyList<LessonCache>()
            override fun changeExpected(expected: List<LessonCache>) {
                data = expected
            }

            override fun checkCalledLessons() {
                functionCallsStack.checkCalled(REPOSITORY_CALLED)
            }

            override suspend fun lessons(): List<LessonCache> {
                functionCallsStack.put(REPOSITORY_CALLED)
                return data
            }
        }

        companion object {
            private const val REPOSITORY_CALLED = "repository#lessons"
        }
    }

    private interface FakeLessonsListCommunication : LessonsListCommunication {

        fun same(other: LessonsUi)

        class Base(private val functionCallsStack: FunctionsCallsStack) :
            FakeLessonsListCommunication {
            private val list = mutableListOf<LessonsUi>()
            private var index = 0

            override fun map(source: LessonsUi) {
                functionCallsStack.put(COMMUNICATION_CALLED)
                list.add(source)
            }

            override fun same(other: LessonsUi) {
                assertEquals(list[index++], other)
                functionCallsStack.checkCalled(COMMUNICATION_CALLED)
            }
        }

        companion object {
            private const val COMMUNICATION_CALLED = "communication#map"
        }
    }

    private class FakeLessonsUiMapper : LessonCache.Mapper<LessonUi> {

        override fun map(id: Int, isComplete: Boolean): LessonUi =
            LessonUi(
                id,
                if (isComplete) LessonState.Complete else LessonState.InWork
            )
    }

    private class FakeLessonCache(private val id: Int, private val isComplete: Boolean) :
        LessonCache {
        override fun <T> map(mapper: LessonCache.Mapper<T>): T = mapper.map(id, isComplete)
    }
}
