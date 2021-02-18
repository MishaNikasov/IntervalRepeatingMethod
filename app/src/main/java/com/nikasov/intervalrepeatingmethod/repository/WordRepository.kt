package com.nikasov.intervalrepeatingmethod.repository

import androidx.lifecycle.LiveData
import com.nikasov.intervalrepeatingmethod.data.domain.Word
import com.nikasov.intervalrepeatingmethod.data.mapper.WordMapper
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordDao
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordEntity
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val wordDao: WordDao,
    private val wordMapper: WordMapper
) {
    suspend fun insertWord(word: Word) { wordDao.insertWord(wordMapper.mapToEntity(word)) }
    fun readAllWords(): LiveData<List<WordEntity>> = wordDao.readAllWords()

    fun getWordList(list: List<WordEntity>) = wordMapper.mapFromEntityList(list)
}