package ru.easycode.words504.translate.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translate_table")
data class TranslateEntity(
    @PrimaryKey
    private val id: String,
    private val text: String,
    private val targetLang: String
)
