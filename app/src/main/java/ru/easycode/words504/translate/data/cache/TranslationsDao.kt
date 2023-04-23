package ru.easycode.words504.translate.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.easycode.words504.translate.data.cache.entities.TranslationCache

@Dao
interface TranslationsDao {

    @Query(
        "SELECT EXISTS(" +
        "SELECT * FROM translations_table " +
        "WHERE id = :id AND targetLang = :targetLang)"
    )
    fun isExistTranslationByIdAndLang(id: String, targetLang: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tr: TranslationCache)

    @Query("DELETE FROM translations_table WHERE targetLang = :targetLang")
    fun deleteTranslation(targetLang: String)

    @Query("SELECT * FROM translations_table WHERE id = :id")
    fun getTranslationById(id: String): TranslationCache
}
