package ru.easycode.words504.initial.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.BaseTest
import ru.easycode.words504.languages.data.cache.ChosenLanguageCache
import ru.easycode.words504.languages.data.cache.LanguageCache
import ru.easycode.words504.languages.data.cache.LanguagesCacheDataSource
import ru.easycode.words504.languages.data.cloud.LanguageCloud
import ru.easycode.words504.languages.data.cloud.LanguageCloudCacheMapper
import ru.easycode.words504.languages.data.cloud.LanguagesCloudDataSource

class BaseInitialRepositoryTest : BaseTest() {

    private lateinit var repository: BaseInitialRepository
    private lateinit var fakeChosenCache: FakeChosenCache
    private lateinit var fakeLanguagesCache: FakeLanguagesCache
    private lateinit var fakeLanguagesCloud: FakeLanguagesCloud
    private lateinit var languageMapper: LanguageCloudCacheMapper

    @Before
    fun setup() {
        fakeChosenCache = FakeChosenCache.Base(functionsCallsStack)
        fakeLanguagesCache = FakeLanguagesCache.Base(functionsCallsStack)
        fakeLanguagesCloud = FakeLanguagesCloud.Base(functionsCallsStack)
        languageMapper = LanguageCloudCacheMapper()
        repository = BaseInitialRepository(
            chosenCache = fakeChosenCache,
            languagesCache = fakeLanguagesCache,
            languagesCloud = fakeLanguagesCloud,
            languageMapper = languageMapper
        )
    }

    @Test
    fun `no chosen language`() {
        fakeChosenCache.setCache(LanguageCache.Base("", ""))
        val expected = false
        val result = repository.userHasChosenLanguage()
        assertEquals(expected, result)
        fakeChosenCache.checkReadCalled()
        functionsCallsStack.checkStack(1)
    }

    @Test
    fun `there is chosen language`() {
        fakeChosenCache.setCache(LanguageCache.Base("es", "Spanish"))
        val expected = true
        val result = repository.userHasChosenLanguage()
        assertEquals(expected, result)
        fakeChosenCache.checkReadCalled()
        functionsCallsStack.checkStack(1)
    }

    @Test
    fun `init save cloud to local`() = runBlocking {
        val cloud = listOf(
            LanguageCloud.Base("en", "English"),
            LanguageCloud.Base("fr", "French"),
            LanguageCloud.Base("de", "German")
        )
        fakeLanguagesCloud.setCloud(cloud)
        repository.init()
        val expected = listOf(
            LanguageCache.Base("en", "English"),
            LanguageCache.Base("fr", "French"),
            LanguageCache.Base("de", "German")
        )
        assertEquals(expected, fakeLanguagesCache.saved())
        fakeLanguagesCloud.checkLanguagesCalled()
        fakeLanguagesCache.checkSaveCalled()
        functionsCallsStack.checkStack(2)
    }

    private interface FakeChosenCache : ChosenLanguageCache.Read {

        fun setCache(cache: LanguageCache)

        fun checkReadCalled()

        class Base(
            private val functionsCallStack: FunctionsCallsStack
        ) : FakeChosenCache {

            private var savedCache: LanguageCache? = null

            override fun checkReadCalled() {
                functionsCallStack.checkCalled(READ_CALLED)
            }

            override fun setCache(cache: LanguageCache) {
                savedCache = cache
            }

            override fun read(): LanguageCache {
                functionsCallStack.put(READ_CALLED)
                return savedCache!!
            }

            companion object {

                const val READ_CALLED = "READ_CHOSEN_CALLED"
            }
        }
    }

    private interface FakeLanguagesCache : LanguagesCacheDataSource.Save {

        fun checkSaveCalled()

        fun saved(): List<LanguageCache>

        class Base(
            private val functionsCallStack: FunctionsCallsStack
        ) : FakeLanguagesCache {

            private var savedCache: List<LanguageCache>? = null

            override fun saved(): List<LanguageCache> = savedCache!!

            override fun checkSaveCalled() {
                functionsCallStack.checkCalled(SAVE_CALLED)
            }

            override fun save(data: List<LanguageCache>) {
                functionsCallStack.put(SAVE_CALLED)
                savedCache = data
            }

            companion object {

                const val SAVE_CALLED = "SAVE_LANGUAGES_CACHE_CALLED"
            }
        }
    }

    private interface FakeLanguagesCloud : LanguagesCloudDataSource {

        fun setCloud(cloud: List<LanguageCloud>)

        fun checkLanguagesCalled()

        class Base(
            private val functionsCallStack: FunctionsCallsStack
        ) : FakeLanguagesCloud {

            private var savedCloud: List<LanguageCloud>? = null

            override fun checkLanguagesCalled() {
                functionsCallStack.checkCalled(READ_CALLED)
            }

            override fun setCloud(cloud: List<LanguageCloud>) {
                savedCloud = cloud
            }

            override suspend fun languages(): List<LanguageCloud> {
                functionsCallStack.put(READ_CALLED)
                return savedCloud!!
            }

            companion object {

                const val READ_CALLED = "READ_LANGUAGES_CLOUD_CALLED"
            }
        }
    }
}
