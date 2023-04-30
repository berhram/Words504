package ru.easycode.words504.initial.domain

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.domain.HandleError

/**
 * @author Asatryan on 30.04.2023
 */
class InitialInteractorTest : BaseTest() {

    private lateinit var repository: FakeRepository
    private lateinit var handleError: FakeHandleError
    private lateinit var interactor: InitialInteractor

    @Before
    fun setup() {
        repository = FakeRepository.Base(functionsCallsStack)
        handleError = FakeHandleError.Base(functionsCallsStack)
        interactor = InitialInteractor.Base(repository = repository, handleError = handleError)
    }

    @Test
    fun `test not first opening`(): Unit = runBlocking {
        repository.userHasChosenLanguage(true)
        val actual = interactor.init()
        repository.checkCalledUserHasChosenLanguage()
        val expected = InitialResult.NotFirstOpening
        assertEquals(expected, actual)
        functionsCallsStack.checkStack(1)
    }

    @Test
    fun `test first opening successful`(): Unit = runBlocking {
        repository.userHasChosenLanguage(false)
        repository.initCalled(success = true)
        val actual = interactor.init()
        repository.checkCalledUserHasChosenLanguage()
        repository.checkCalledInit()
        val expected = InitialResult.FirstOpening
        assertEquals(expected, actual)
        functionsCallsStack.checkStack(2)
    }

    @Test
    fun `test first opening failure`(): Unit = runBlocking {
        repository.userHasChosenLanguage(false)
        repository.initCalled(success = false)
        val actual = interactor.init()
        repository.checkCalledUserHasChosenLanguage()
        repository.checkCalledInit()
        handleError.checkCalledHandle()
        val expected = InitialResult.Error("something went wrong")
        assertEquals(expected, actual)
        functionsCallsStack.checkStack(3)
    }

    private interface FakeRepository : InitialRepository {

        fun userHasChosenLanguage(chosen: Boolean)

        fun checkCalledUserHasChosenLanguage()

        fun checkCalledInit()

        fun initCalled(success: Boolean)

        class Base(private val functionsCallsStack: FunctionsCallsStack) : FakeRepository {
            private var userHasChosenLanguage: Boolean? = null
            private var initCalledSuccessfully: Boolean? = null

            override fun userHasChosenLanguage(chosen: Boolean) {
                userHasChosenLanguage = chosen
            }

            override fun checkCalledUserHasChosenLanguage() {
                functionsCallsStack.checkCalled(USER_HAS_CHOSEN_LANGUAGES_CALLED)
            }

            override fun checkCalledInit() {
                functionsCallsStack.checkCalled(INIT_CALLED)
            }

            override fun userHasChosenLanguage(): Boolean {
                functionsCallsStack.put(USER_HAS_CHOSEN_LANGUAGES_CALLED)
                return userHasChosenLanguage!!
            }

            override fun initCalled(success: Boolean) {
                initCalledSuccessfully = success
            }

            override suspend fun init() {
                functionsCallsStack.put(INIT_CALLED)
                if (initCalledSuccessfully == false) throw FakeException()
            }

            companion object {
                private const val INIT_CALLED = "INIT_CALLED"
                private const val USER_HAS_CHOSEN_LANGUAGES_CALLED =
                    "USER_HAS_CHOSEN_LANGUAGES_CALLED"
            }
        }
    }

    private class FakeException : Exception()

    private interface FakeHandleError : HandleError<Exception, String> {

        fun checkCalledHandle()

        class Base(private val functionsCallsStack: FunctionsCallsStack) : FakeHandleError {

            override fun checkCalledHandle() {
                functionsCallsStack.checkCalled(HANDLE_CALLED)
            }

            override fun handle(source: Exception): String {
                functionsCallsStack.put(HANDLE_CALLED)
                if (source is FakeException) return "something went wrong"
                throw java.lang.IllegalStateException("unknown exception type $source")
            }

            companion object {
                const val HANDLE_CALLED = "HANDLE_CALLED"
            }
        }
    }
}
