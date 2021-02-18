package com.nikasov.intervalrepeatingmethod.data.mapper

import com.nikasov.intervalrepeatingmethod.data.domain.Word
import com.nikasov.intervalrepeatingmethod.data.domain.WordState
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordEntity
import java.util.*
import javax.inject.Inject

class WordMapper @Inject constructor(): EntityMapper<WordEntity, Word> {
    override fun mapFromEntity(entity: WordEntity): Word {
        return Word(
            id = entity.id,
            eng = entity.eng,
            rus = entity.rus,
            createDate = calendarFromTimestamp(entity.createDate),
            repeatDate = calendarFromTimestamp(entity.repeatDate),
            state = getStateFromId(entity.state)
        )
    }
    override fun mapToEntity(domainModel: Word): WordEntity {
        return WordEntity(
            eng = domainModel.eng,
            rus = domainModel.rus,
            createDate = dateToTimestamp(domainModel.createDate),
            repeatDate = dateToTimestamp(domainModel.repeatDate),
            state = getIdState(domainModel.state)
        )
    }


    private fun calendarFromTimestamp(value: Long): Calendar {
        val cal: Calendar = GregorianCalendar()
        cal.timeInMillis = value * 1000
        return cal
    }

    private fun dateToTimestamp(cal: Calendar?): Long {
        cal?.let {
            return cal.timeInMillis / 1000
        }
        return -1
    }

    private fun getStateFromId(id: Int): WordState {
        return when (id) {
            0 -> WordState.FIRST
            1 -> WordState.SECOND
            2 -> WordState.THIRD
            3 -> WordState.FOURTH
            else -> WordState.DEFAULT
        }
    }

    private fun getIdState(wordState: WordState): Int {
        return wordState.id
    }
}
