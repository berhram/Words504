package ru.easycode.words504.translate.data.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "translate_table")
data class TranslateEntity(
    @PrimaryKey
    val id: String,
    val text: String,
    val targetLang: String
) : Serializable