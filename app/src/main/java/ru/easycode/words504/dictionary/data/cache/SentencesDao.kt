package ru.easycode.words504.dictionary.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.easycode.words504.dictionary.data.cache.entities.SentenceCache

@Dao
interface SentencesDao {

    @Query("SELECT * FROM sentence_table WHERE id = :id")
    fun sentence(id: Int): SentenceCache

    @Query(
        "SELECT * FROM sentence_table WHERE id IN" +
            " (SELECT sentenceId FROM words_table WHERE dictionaryForm = :dictionaryForm)"
    )
    fun sentences(dictionaryForm: String): List<SentenceCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sentence: SentenceCache)

    @Query("DELETE FROM sentence_table WHERE id = :id")
    fun deleteSentence(id: Int)
}
