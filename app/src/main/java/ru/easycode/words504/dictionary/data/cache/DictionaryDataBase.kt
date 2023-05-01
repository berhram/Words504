package ru.easycode.words504.dictionary.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.easycode.words504.dictionary.data.cache.entities.SentenceCache
import ru.easycode.words504.dictionary.data.cache.entities.WordCache
import ru.easycode.words504.translate.data.cache.TranslationsDao
import ru.easycode.words504.translate.data.cache.entities.TranslationCache

interface DictionaryDataBase {

    fun wordsDao(): WordsDao
    fun translationDao(): TranslationsDao

    @Database(
        entities = [SentenceCache::class, WordCache::class, TranslationCache::class],
        version = 1
    )
    abstract class Base : RoomDatabase(), DictionaryDataBase
}
