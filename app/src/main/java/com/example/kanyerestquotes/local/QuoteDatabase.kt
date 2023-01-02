package com.example.kanyerestquotes.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kanyerestquotes.data.model.KanyeData

@Database(entities = [KanyeData::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract val QuoteDatabaseDao : QuoteDatabaseDao
}

private lateinit var INSTANCE: QuoteDatabase


fun getDatabase(context: Context): QuoteDatabase {
    synchronized(QuoteDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            QuoteDatabase::class.java,"QuoteDatabase").build()
        }
    }
    return INSTANCE
}

