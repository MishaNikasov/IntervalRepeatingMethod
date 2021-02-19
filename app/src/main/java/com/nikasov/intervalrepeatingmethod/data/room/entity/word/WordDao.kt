package com.nikasov.intervalrepeatingmethod.data.room.entity.word

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert
    suspend fun insertWord(wordEntity: WordEntity)
    @Query("SELECT * FROM WORDS_TABLE ORDER BY createDate ASC")
    fun readAllWords(): LiveData<List<WordEntity>>
    @Query("SELECT * FROM WORDS_TABLE WHERE repeatDate IS NULL ORDER BY createDate ASC")
    fun readAllUncompletedWords(): LiveData<List<WordEntity>>
    @Query("DELETE FROM WORDS_TABLE WHERE id = :id")
    suspend fun deleteWordById(id: Int)
}