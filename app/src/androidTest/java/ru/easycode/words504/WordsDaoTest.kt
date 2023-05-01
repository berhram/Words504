package ru.easycode.words504

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.dictionary.data.cache.DictionaryDataBase
import ru.easycode.words504.dictionary.data.cache.WordsDao
import ru.easycode.words504.dictionary.data.cache.entities.SentenceCache
import ru.easycode.words504.dictionary.data.cache.entities.WordCache
import ru.easycode.words504.translate.data.cache.TranslationsDao
import ru.easycode.words504.translate.data.cache.entities.TranslationCache

class WordsDaoTest {
    private lateinit var dataBase: DictionaryDataBase.Base
    private lateinit var wordsDao: WordsDao
    private lateinit var translationsDao: TranslationsDao

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        dataBase = Room.inMemoryDatabaseBuilder(context, DictionaryDataBase.Base::class.java)
            .allowMainThreadQueries()
            .build()
        wordsDao = dataBase.wordsDao()
        translationsDao = dataBase.translationDao()

        wordsDao.insert(WordCache(1, "He", 0, "he", 1))
        wordsDao.insert(WordCache(2, "goes", 3, "to go", 1))
        wordsDao.insert(WordCache(3, "into", 8, "into", 1))
        wordsDao.insert(WordCache(4, "the", 13, "the", 1))
        wordsDao.insert(WordCache(5, "synagogue", 17, "synagogue", 1))

        wordsDao.insert(WordCache(10, "She", 0, "she", 2))
        wordsDao.insert(WordCache(11, "went", 4, "to go", 2))
        wordsDao.insert(WordCache(12, "to", 9, "to", 2))
        wordsDao.insert(WordCache(13, "the", 12, "the", 2))
        wordsDao.insert(WordCache(14, "theatre", 16, "theatre", 2))

        wordsDao.insert(SentenceCache(1, "He goes into the synagogue"))
        wordsDao.insert(SentenceCache(2, "She went to the theatre"))

        translationsDao.insert(TranslationCache("he", "он", "ru"))
        translationsDao.insert(TranslationCache("to go", "идти", "ru"))
        translationsDao.insert(TranslationCache("into", "в", "ru"))
        translationsDao.insert(TranslationCache("the", "этот", "ru"))
        translationsDao.insert(TranslationCache("synagogue", "синагога", "ru"))

        translationsDao.insert(TranslationCache("she", "она", "ru"))
        translationsDao.insert(TranslationCache("to", "в", "ru"))
        translationsDao.insert(TranslationCache("theatre", "театр", "ru"))

        translationsDao.insert(TranslationCache("1", "Он ходит в синагогу", "ru"))
        translationsDao.insert(TranslationCache("2", "Она пошла в театр", "ru"))
    }

    @Test
    fun test_word_info_by_dictionary_form() {
        val sourceWord = "to go"
        val sourceTranslation = translationsDao.translation(sourceWord)

        assertEquals(TranslationCache("to go", "идти", "ru"), sourceTranslation)

        val sentences = wordsDao.sentences(sourceWord)
        val comboList = sentences.map { sentence ->
            val sentenceTranslation = translationsDao.translation(sentence.id.toString())
            val words = wordsDao.words(sentence.id)
            val wordsComboList = words.map { word ->
                val translation = translationsDao.translation(word.dictionaryForm)
                FakeWordCombo(word, translation)
            }
            FakeCombo(sentence, sentenceTranslation, wordsComboList)
        }
        val expectedCombo = listOf(
            FakeCombo(
                sentence = SentenceCache(1, "He goes into the synagogue"),
                sentenceTranslation = TranslationCache("1", "Он ходит в синагогу", "ru"),
                words = listOf(
                    FakeWordCombo(
                        wordCache = WordCache(1, "He", 0, "he", 1),
                        translationCache = TranslationCache("he", "он", "ru")
                    ),
                    FakeWordCombo(
                        wordCache = WordCache(2, "goes", 3, "to go", 1),
                        translationCache = TranslationCache("to go", "идти", "ru")
                    ),
                    FakeWordCombo(
                        wordCache = WordCache(3, "into", 8, "into", 1),
                        translationCache = TranslationCache("into", "в", "ru")
                    ),
                    FakeWordCombo(
                        wordCache = WordCache(4, "the", 13, "the", 1),
                        translationCache = TranslationCache("the", "этот", "ru")
                    ),
                    FakeWordCombo(
                        wordCache = WordCache(5, "synagogue", 17, "synagogue", 1),
                        translationCache = TranslationCache("synagogue", "синагога", "ru")
                    )
                )
            ),
            FakeCombo(
                sentence = SentenceCache(2, "She went to the theatre"),
                sentenceTranslation = TranslationCache("2", "Она пошла в театр", "ru"),
                words = listOf(
                    FakeWordCombo(
                        wordCache = WordCache(10, "She", 0, "she", 2),
                        translationCache = TranslationCache("she", "она", "ru")
                    ),
                    FakeWordCombo(
                        wordCache = WordCache(11, "went", 4, "to go", 2),
                        translationCache = TranslationCache("to go", "идти", "ru")
                    ),
                    FakeWordCombo(
                        wordCache = WordCache(12, "to", 9, "to", 2),
                        translationCache = TranslationCache("to", "в", "ru")
                    ),
                    FakeWordCombo(
                        wordCache = WordCache(13, "the", 12, "the", 2),
                        translationCache = TranslationCache("the", "этот", "ru")
                    ),
                    FakeWordCombo(
                        wordCache = WordCache(14, "theatre", 16, "theatre", 2),
                        translationCache = TranslationCache("theatre", "театр", "ru")
                    )
                )
            )
        )
        assertEquals(expectedCombo, comboList)
    }

    private data class FakeCombo(
        private val sentence: SentenceCache,
        private val sentenceTranslation: TranslationCache,
        private val words: List<FakeWordCombo>
    )

    private data class FakeWordCombo(
        private val wordCache: WordCache,
        private val translationCache: TranslationCache
    )
}
