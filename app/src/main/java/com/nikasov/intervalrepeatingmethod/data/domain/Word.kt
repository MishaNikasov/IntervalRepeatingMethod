package com.nikasov.intervalrepeatingmethod.data.domain

import java.util.*

data class Word (
    val id: Int? = null,
    val eng: String,
    val rus: String,
    val createDate: Calendar,
    val repeatDate: Calendar? = null,
    val repeatCount: Int = 0,
    val failCount: Int = 0,
    val isActive: Boolean = false,
    val isFavorite: Boolean = false,
    val isCompleted: Boolean = false
)