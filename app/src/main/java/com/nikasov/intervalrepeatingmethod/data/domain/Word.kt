package com.nikasov.intervalrepeatingmethod.data.domain

import java.util.*

data class Word (
    val id: Int? = null,
    val eng: String,
    val rus: String,
    val createDate: Calendar,
    val repeatDate: Calendar? = null,
    val state: WordState = WordState.DEFAULT
)

enum class WordState(val id: Int) {
    DEFAULT(-1),
    FIRST(0),
    SECOND(1),
    THIRD(2),
    FOURTH(3)
}