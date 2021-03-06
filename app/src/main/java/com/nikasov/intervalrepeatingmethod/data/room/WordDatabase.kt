package com.nikasov.intervalrepeatingmethod.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikasov.intervalrepeatingmethod.data.room.dao.WordDao
import com.nikasov.intervalrepeatingmethod.data.room.entity.WordEntity

@Database(entities = [WordEntity::class], version = 4, exportSchema = false)
abstract class WordDatabase: RoomDatabase() {
    abstract fun getWordDao(): WordDao
}