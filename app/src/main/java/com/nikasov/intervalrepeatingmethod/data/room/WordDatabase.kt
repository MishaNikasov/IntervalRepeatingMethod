package com.nikasov.intervalrepeatingmethod.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordDao
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordEntity

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordDatabase: RoomDatabase() {
    abstract fun getWordDao(): WordDao
}