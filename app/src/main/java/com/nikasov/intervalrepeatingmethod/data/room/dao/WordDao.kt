package com.nikasov.intervalrepeatingmethod.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nikasov.intervalrepeatingmethod.data.room.entity.WordEntity

@Dao
interface WordDao {
    @Insert
    suspend fun insertWord(wordEntity: WordEntity)
    @Query("DELETE FROM WORDS_TABLE WHERE id = :id")
    suspend fun deleteWordById(id: Int)

    @Query("SELECT * FROM WORDS_TABLE ORDER BY createDate ASC")
    fun readAllWords(): LiveData<List<WordEntity>>
    @Query("SELECT * FROM WORDS_TABLE WHERE repeatDate IS NULL OR repeatDate < :currentDate ORDER BY createDate ASC")
    suspend fun readAllUncompletedWords(currentDate: Long): List<WordEntity>

    @Query("UPDATE WORDS_TABLE SET failCount = failCount + 1, repeatCount = 0, repeatDate = :repeatDate WHERE id = :id")
    suspend fun wordFailed(id: Int, repeatDate: Long)
    @Query("UPDATE WORDS_TABLE SET repeatCount = repeatCount + 1, repeatDate = :repeatDate WHERE id = :id")
    suspend fun wordCorrect(id: Int, repeatDate: Long)

}