package com.nikasov.intervalrepeatingmethod.data.room.entity.word

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Insert
    suspend fun insertWord(wordEntity: WordEntity)
    @Query("SELECT * FROM words_table ORDER BY createDate ASC")
    fun readAllWords(): LiveData<List<WordEntity>>
}