package ru.easycode.words504.translate.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WordCache")
data class WordCache(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "uiForm")
    val uiForm: String,
    @ColumnInfo(name = "index")
    val index: Int,
    @ColumnInfo(name = "dictionaryForm")
    val dictionaryForm: String,
    @ColumnInfo(name = "sentenceId")
    val sentenceId: Int
)
