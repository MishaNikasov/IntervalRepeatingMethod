package com.nikasov.intervalrepeatingmethod.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nikasov.intervalrepeatingmethod.ui.entity.WordModel
import com.nikasov.intervalrepeatingmethod.data.mapper.WordMapper
import com.nikasov.intervalrepeatingmethod.data.room.dao.WordDao
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val wordDao: WordDao,
    private val wordMapper: WordMapper
) {
    suspend fun insertWord(wordModel: WordModel) { wordDao.insertWord(wordMapper.mapToEntity(wordModel)) }
    suspend fun deleteWordById(id: Int) { wordDao.deleteWordById(id) }

    suspend fun wordFailed(id: Int, repeatDate: Long){ wordDao.wordFailed(id, repeatDate) }
    suspend fun wordCorrect(id: Int, repeatDate: Long){ wordDao.wordCorrect(id, repeatDate) }

    suspend fun readAllUncompletedWords(currentDate: Long): List<WordModel> = wordMapper.mapFromEntityList(wordDao.readAllUncompletedWords(currentDate))

    fun readAllWords(): LiveData<List<WordModel>> = Transformations.map(wordDao.readAllWords()) {
            list -> return@map wordMapper.mapFromEntityList(list)
    }
}