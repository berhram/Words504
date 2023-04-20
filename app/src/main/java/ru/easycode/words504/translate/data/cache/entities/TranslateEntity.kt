package ru.easycode.words504.translate.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.easycode.words504.data.cache.serialization.Serialization

@Entity(tableName = "translate_table")
data class TranslateCache(
    @PrimaryKey @ColumnInfo(name = "id") private val id: String,
    @ColumnInfo(name = "text") private val text: String,
    @ColumnInfo(name = "targetLang") private val targetLang: String
)
