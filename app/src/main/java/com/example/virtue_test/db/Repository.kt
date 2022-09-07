package com.example.virtue_test.db

import com.example.virtue_test.db.models.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val database: Database,
    private val api: ApiInterface
) {

    fun getAllLocalQoutes(): List<Quote>{
        return database.qouteDao().getAllQuote()
    }

    suspend fun deleteQuote(quote: Quote){
        withContext(Dispatchers.IO){
            database.qouteDao().deleteQuote(quote)
        }
    }

    suspend fun insertQuote(quote: Quote){
        withContext(Dispatchers.IO){
            database.qouteDao().addQuote(quote)
        }
    }

    suspend fun getAllQuotes(): Response<ArrayList<Quote>> {
        return api.getAllQoute()
    }

}