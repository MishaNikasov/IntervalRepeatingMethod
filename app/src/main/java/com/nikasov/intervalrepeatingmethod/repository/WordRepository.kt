package com.nikasov.intervalrepeatingmethod.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Query
import com.nikasov.intervalrepeatingmethod.data.domain.Word
import com.nikasov.intervalrepeatingmethod.data.mapper.WordMapper
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordDao
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val wordDao: WordDao,
    private val wordMapper: WordMapper
) {
    suspend fun insertWord(word: Word) { wordDao.insertWord(wordMapper.mapToEntity(word)) }
    suspend fun deleteWordById(id: Int) { wordDao.deleteWordById(id) }

    suspend fun wordFailed(id: Int, repeatDate: Long){ wordDao.wordFailed(id, repeatDate) }
    suspend fun wordCorrect(id: Int, repeatDate: Long){ wordDao.wordCorrect(id, repeatDate) }

    suspend fun readAllUncompletedWords(currentDate: Long): List<Word> = wordMapper.mapFromEntityList(wordDao.readAllUncompletedWords(currentDate))

    fun readAllWords(): LiveData<List<Word>> = Transformations.map(wordDao.readAllWords()) {
            list -> return@map wordMapper.mapFromEntityList(list)
    }
}