package ru.easycode.words504.dictionary.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.easycode.words504.dictionary.data.cache.entities.SentenceCache
import ru.easycode.words504.dictionary.data.cache.entities.WordCache
import ru.easycode.words504.translate.data.cache.entities.TranslationCache

@Dao
interface WordsDao {

    @Query("SELECT * FROM words_table WHERE dictionaryForm = :dictionaryForm")
    fun words(dictionaryForm: String): List<WordCache>

    @Query("SELECT * FROM words_table WHERE sentenceId = :sentenceId")
    fun words(sentenceId: Int): List<WordCache>

    @Query("SELECT * FROM sentence_table WHERE id = :id")
    fun sentence(id: Int): SentenceCache

    @Query(
        "SELECT * FROM sentence_table WHERE id IN" +
            " (SELECT sentenceId FROM words_table WHERE dictionaryForm = :dictionaryForm)"
    )
    fun sentences(dictionaryForm: String): List<SentenceCache>

    @Query(
        "SELECT * FROM translations_table WHERE id IN" +
            " (SELECT uiForm FROM words_table WHERE sentenceId = :sentenceId)"
    )
    fun translationWords(sentenceId: Int): List<TranslationCache>

    @Query(
        "SELECT * FROM translations_table WHERE id IN " +
            "(SELECT uiForm FROM words_table WHERE sentenceId IN " +
            "(SELECT sentenceId FROM words_table WHERE dictionaryForm = :dictionaryForm))"
    )
    fun translationWords(dictionaryForm: String): List<TranslationCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: WordCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sentence: SentenceCache)

    @Query("DELETE FROM sentence_table WHERE id = :id")
    fun deleteSentence(id: Int)

    @Query("DELETE FROM words_table WHERE id = :id")
    fun deleteWord(id: Int)
}
