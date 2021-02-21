package com.nikasov.intervalrepeatingmethod.data.room.entity.word

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "words_table")
data class WordEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val eng: String,
    val rus: String,
    val createDate: Long,
    val repeatDate: Long? = null,
    val repeatCount: Int = 0,
    val failCount: Int = 0,
    val isActive: Boolean = false,
    val isFavorite: Boolean = false,
    val isCompleted: Boolean = false
)