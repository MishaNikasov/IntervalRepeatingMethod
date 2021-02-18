package com.nikasov.intervalrepeatingmethod.di

import android.content.Context
import androidx.room.Room
import com.nikasov.intervalrepeatingmethod.data.room.WordDatabase
import com.nikasov.intervalrepeatingmethod.data.room.entity.word.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val WORD_DATABASE = "word_database"

    @Provides
    @Singleton
    fun provideWordDatabase(@ApplicationContext context: Context): WordDatabase {
        return Room.databaseBuilder(
            context,
            WordDatabase::class.java,
            WORD_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideWordDao(wordDatabase: WordDatabase): WordDao {
        return wordDatabase.getWordDao()
    }
}