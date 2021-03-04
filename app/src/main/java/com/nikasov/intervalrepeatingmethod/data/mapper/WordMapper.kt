package com.nikasov.intervalrepeatingmethod.data.mapper

import com.nikasov.intervalrepeatingmethod.ui.entity.WordModel
import com.nikasov.intervalrepeatingmethod.data.room.entity.WordEntity
import java.util.*
import javax.inject.Inject

class WordMapper @Inject constructor() : EntityMapper<WordEntity, WordModel> {
    override fun mapFromEntity(entity: WordEntity): WordModel {
        return WordModel(
            id = entity.id,
            eng = entity.eng,
            rus = entity.rus,
            createDate = calendarFromTimestamp(entity.createDate) ?: Calendar.getInstance(),
            repeatDate = calendarFromTimestamp(entity.repeatDate),
            repeatCount = entity.repeatCount,
            failCount = entity.failCount,
            isActive = entity.isActive,
            isFavorite = entity.isFavorite,
            isCompleted = entity.isCompleted
        )
    }

    override fun mapToEntity(domainModel: WordModel): WordEntity {
        return WordEntity(
            eng = domainModel.eng,
            rus = domainModel.rus,
            createDate = dateToTimestamp(domainModel.createDate) ?: 0,
            repeatDate = dateToTimestamp(domainModel.repeatDate),
            repeatCount = domainModel.repeatCount,
            failCount = domainModel.failCount,
            isActive = domainModel.isActive,
            isFavorite = domainModel.isFavorite,
            isCompleted = domainModel.isCompleted
        )
    }


    private fun calendarFromTimestamp(value: Long?): Calendar? {
        return if (value == null)
            null
        else {
            val cal: Calendar = GregorianCalendar()
            cal.time = Date(value)
            cal
        }
    }

    private fun dateToTimestamp(cal: Calendar?): Long? {
        return if (cal == null)
            null
        else {
            return cal.time.time
        }
    }
}
