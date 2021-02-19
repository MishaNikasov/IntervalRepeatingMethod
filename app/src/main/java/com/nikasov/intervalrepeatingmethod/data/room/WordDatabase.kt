package com.nikasov.intervalrepeatingmethod.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordDao
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordEntity

@Database(entities = [WordEntity::class], version = 2, exportSchema = false)
abstract class WordDatabase: RoomDatabase() {
    abstract fun getWordDao(): WordDao
}