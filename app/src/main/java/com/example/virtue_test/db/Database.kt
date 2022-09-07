package com.example.virtue_test.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.virtue_test.db.dao.QuoteDao
import com.example.virtue_test.db.models.Quote

@Database(entities = arrayOf(Quote::class), version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun qouteDao(): QuoteDao
}