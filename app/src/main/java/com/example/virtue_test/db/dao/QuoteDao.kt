package com.example.virtue_test.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.virtue_test.db.models.Quote

@Dao
interface QuoteDao {
    @Query("SELECT * FROM QUOTE")
    fun getAllQuote(): List<Quote>

    @Insert
    fun addQuote(quote: Quote)

    @Delete
    fun deleteQuote(quote: Quote)
}