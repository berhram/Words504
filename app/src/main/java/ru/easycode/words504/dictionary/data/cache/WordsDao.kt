package ru.easycode.words504.dictionary.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.easycode.words504.dictionary.data.cache.entities.SentenceCache
import ru.easycode.words504.dictionary.data.cache.entities.WordCache

@Dao
interface WordsDao {

    @Query("SELECT * FROM words_table WHERE sentenceId = :sentenceId")
    fun words(sentenceId: Int): List<WordCache>

    @Query(
        "SELECT * FROM sentence_table WHERE id IN" +
            " (SELECT sentenceId FROM words_table WHERE dictionaryForm = :dictionaryForm)"
    )
    fun sentences(dictionaryForm: String): List<SentenceCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sentence: SentenceCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: WordCache)
}
