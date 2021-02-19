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
            createDate = calendarFromTimestamp(entity.createDate) ?: Calendar.getInstance(),
            repeatDate = calendarFromTimestamp(entity.repeatDate),
            state = getStateFromId(entity.state),
            isActive = entity.isActive,
            isFavorite = entity.isFavorite
        )
    }
    override fun mapToEntity(domainModel: Word): WordEntity {
        return WordEntity(
            eng = domainModel.eng,
            rus = domainModel.rus,
            createDate = dateToTimestamp(domainModel.createDate) ?: 0,
            repeatDate = dateToTimestamp(domainModel.repeatDate),
            state = getIdState(domainModel.state),
            isActive = domainModel.isActive,
            isFavorite = domainModel.isFavorite
        )
    }


    private fun calendarFromTimestamp(value: Long?): Calendar? {
        return if (value == null)
            null
        else {
            val cal: Calendar = GregorianCalendar()
            cal.timeInMillis = value * 1000
            cal
        }
    }

    private fun dateToTimestamp(cal: Calendar?): Long? {
        return if (cal == null)
            null
        else {
            return cal.timeInMillis / 1000
        }
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
