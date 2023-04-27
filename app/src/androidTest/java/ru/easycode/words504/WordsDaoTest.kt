package ru.easycode.words504

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.words504.dictionary.data.cache.SentencesDao
import ru.easycode.words504.dictionary.data.cache.WordsDao
import ru.easycode.words504.dictionary.data.cache.entities.SentenceCache
import ru.easycode.words504.dictionary.data.cache.entities.WordCache
import ru.easycode.words504.translate.data.cache.TranslationsDao
import ru.easycode.words504.translate.data.cache.entities.TranslationCache
import java.io.IOException

interface WordsDaoTest {
    fun fillFakeWordsTable()
    fun fillFakeSentenceTable()
    fun fillFakeTranslationsTable()

    class Base : WordsDaoTest {
        private lateinit var dataBase: AppDataBase
        private lateinit var wordDao: WordsDao
        private lateinit var sentencesDao: SentencesDao
        private lateinit var translationsDao: TranslationsDao

        @Before
        fun setUp() {
            val context: Context = ApplicationProvider.getApplicationContext()
            dataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java)
                .allowMainThreadQueries()
                .build()
            wordDao = dataBase.wordsDao()
            translationsDao = dataBase.translationDao()
            sentencesDao = dataBase.sentencesDao()
        }

        @After
        @Throws(IOException::class)
        fun clear() {
            dataBase.close()
        }

        override fun fillFakeWordsTable() {
            wordDao.insert(WordCache(1, "А", 0, "а", 1))
            wordDao.insert(WordCache(2, "Бе", 2, "б", 1))
            wordDao.insert(WordCache(3, "В", 4, "в", 1))
            wordDao.insert(WordCache(4, "Г", 6, "г", 1))
            wordDao.insert(WordCache(5, "Д", 8, "д", 1))

            wordDao.insert(WordCache(10, "У", 0, "у", 2))
            wordDao.insert(WordCache(11, "Бо", 2, "б", 2))
            wordDao.insert(WordCache(12, "Ж", 4, "ж", 2))
            wordDao.insert(WordCache(13, "З", 6, "з", 2))
            wordDao.insert(WordCache(14, "К", 8, "к", 2))
        }

        override fun fillFakeSentenceTable() {
            sentencesDao.insert(SentenceCache(1, "А Бе В Г Д"))
            sentencesDao.insert(SentenceCache(2, "У Бо Ж З К"))
        }

        override fun fillFakeTranslationsTable() {
            translationsDao.insert(TranslationCache("А", "АА", "1"))
            translationsDao.insert(TranslationCache("Бе", "БеБе", "1"))
            translationsDao.insert(TranslationCache("В", "ВВ", "1"))
            translationsDao.insert(TranslationCache("Г", "ГГ", "1"))
            translationsDao.insert(TranslationCache("Д", "ДД", "1"))

            translationsDao.insert(TranslationCache("У", "УУ", "1"))
            translationsDao.insert(TranslationCache("Бо", "БоБо", "1"))
            translationsDao.insert(TranslationCache("Ж", "ЖЖ", "1"))
            translationsDao.insert(TranslationCache("З", "ЗЗ", "1"))
            translationsDao.insert(TranslationCache("К", "КК", "1"))
        }

        @Test
        fun test_all_words_by_dictionary_form() {
            fillFakeSentenceTable()
            fillFakeWordsTable()

            val expected = listOf(
                WordCache(2, "Бе", 2, "б", 1),
                WordCache(11, "Бо", 2, "б", 2)
            )
            val actual = wordDao.words("б")
            assertEquals(expected, actual)
        }

        @Test
        fun test_all_words_from_sentence_by_sentence_id() {
            fillFakeSentenceTable()
            fillFakeWordsTable()

            val expected = listOf(
                WordCache(10, "У", 0, "у", 2),
                WordCache(11, "Бо", 2, "б", 2),
                WordCache(12, "Ж", 4, "ж", 2),
                WordCache(13, "З", 6, "з", 2),
                WordCache(14, "К", 8, "к", 2)
            )
            val actual = wordDao.words(2)
            assertEquals(expected, actual)
        }

        @Test
        fun test_sentence_by_sentence_id() {
            fillFakeSentenceTable()
            fillFakeWordsTable()
            val expected = SentenceCache(1, "А Бе В Г Д")
            val actual = sentencesDao.sentence(1)
            assertEquals(expected, actual)
        }

        @Test
        fun test_all_sentences_by_word_dictionary_form() {
            fillFakeSentenceTable()
            fillFakeWordsTable()
            val expected = listOf(
                SentenceCache(1, "А Бе В Г Д"),
                SentenceCache(2, "У Бо Ж З К")
            )
            val actual = sentencesDao.sentences("б")
            assertEquals(expected, actual)
        }

        @Test
        fun test_all_word_translations_from_sentence_by_sentence_id() {
            fillFakeSentenceTable()
            fillFakeWordsTable()
            fillFakeTranslationsTable()

            val expected = listOf(
                TranslationCache("А", "АА", "1"),
                TranslationCache("Бе", "БеБе", "1"),
                TranslationCache("В", "ВВ", "1"),
                TranslationCache("Г", "ГГ", "1"),
                TranslationCache("Д", "ДД", "1")
            )
            val actual = wordDao.translationWords(1)
            assertEquals(expected, actual)
        }

        @Test
        fun test_all_word_translations_from_all_sentences_by_dictionary_form() {
            fillFakeSentenceTable()
            fillFakeWordsTable()
            fillFakeTranslationsTable()

            val expected = listOf(
                TranslationCache("А", "АА", "1"),
                TranslationCache("Бе", "БеБе", "1"),
                TranslationCache("В", "ВВ", "1"),
                TranslationCache("Г", "ГГ", "1"),
                TranslationCache("Д", "ДД", "1"),
                TranslationCache("У", "УУ", "1"),
                TranslationCache("Бо", "БоБо", "1"),
                TranslationCache("Ж", "ЖЖ", "1"),
                TranslationCache("З", "ЗЗ", "1"),
                TranslationCache("К", "КК", "1")
            ).sortedBy { it.id }

            val actual = wordDao.translationWords("б").sortedBy { it.id }
            assertEquals(expected, actual)
        }

        @Test
        fun test_insert_and_delete() {
            assertEquals(0, wordDao.words(1).size)
            assertEquals(null, sentencesDao.sentence(1))

            wordDao.insert(WordCache(1, "Hello", 0, "hello", 1))
            assertEquals(1, wordDao.words(1).size)

            sentencesDao.insert(SentenceCache(1, "Hello world"))
            assertEquals(SentenceCache(1, "Hello world"), sentencesDao.sentence(1))

            wordDao.deleteWord(1)
            sentencesDao.deleteSentence(1)
            assertEquals(0, wordDao.words(1).size)
            assertEquals(null, sentencesDao.sentence(1))
        }
    }
}
