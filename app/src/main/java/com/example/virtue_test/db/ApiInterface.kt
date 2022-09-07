package com.example.virtue_test.db

import com.example.virtue_test.db.models.Quote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/")
    suspend fun getAllQoute(
        @Query("cat")
        cat: String = "famous",
        @Query("count")
        count: Int = 10
    ): Response<ArrayList<Quote>>

}