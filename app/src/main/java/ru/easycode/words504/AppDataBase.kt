package ru.easycode.words504

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.easycode.words504.dictionary.data.cache.SentencesDao
import ru.easycode.words504.dictionary.data.cache.WordsDao
import ru.easycode.words504.dictionary.data.cache.entities.SentenceCache
import ru.easycode.words504.dictionary.data.cache.entities.WordCache
import ru.easycode.words504.translate.data.cache.TranslationsDao
import ru.easycode.words504.translate.data.cache.entities.TranslationCache

@Database(entities = [SentenceCache::class, WordCache::class, TranslationCache::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao
    abstract fun sentencesDao(): SentencesDao
    abstract fun translationDao(): TranslationsDao
}
