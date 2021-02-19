package com.nikasov.intervalrepeatingmethod.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nikasov.intervalrepeatingmethod.data.domain.Word
import com.nikasov.intervalrepeatingmethod.data.mapper.WordMapper
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordDao
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val wordDao: WordDao,
    private val wordMapper: WordMapper
) {
    suspend fun insertWord(word: Word) { wordDao.insertWord(wordMapper.mapToEntity(word)) }
    suspend fun deleteWordById(id: Int) { wordDao.deleteWordById(id) }
    fun readAllWords(): LiveData<List<Word>> = Transformations.map(wordDao.readAllWords()) {
            list -> return@map wordMapper.mapFromEntityList(list)
    }
    fun readAllUncompletedWords(): LiveData<List<Word>> = Transformations.map(wordDao.readAllUncompletedWords()) {
            list -> return@map wordMapper.mapFromEntityList(list)
    }
}