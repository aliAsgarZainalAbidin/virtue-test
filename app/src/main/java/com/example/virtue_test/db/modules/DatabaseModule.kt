package com.example.virtue_test.db.modules

import android.content.Context
import androidx.room.Room
import com.example.virtue_test.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun database(
        @ApplicationContext
        context: Context
    ): Database {
        return Room.databaseBuilder(context, Database::class.java, "quote-app.db")
            .fallbackToDestructiveMigration().build()
    }
}