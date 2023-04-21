package ru.easycode.words504.translate.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translations_table")
data class TranslationCache(
    @PrimaryKey
    @ColumnInfo(name = "id")
    private val id: String,
    @ColumnInfo(name = "text")
    private val text: String,
    @ColumnInfo(name = "targetLang")
    private val targetLang: String
)
