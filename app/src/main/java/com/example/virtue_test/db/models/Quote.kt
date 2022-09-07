package com.example.virtue_test.db.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Quote(
    @PrimaryKey()
    @field:SerializedName("id")
    val id: String = "",

    @field:SerializedName("author")
    val author: String? = "",

    @field:SerializedName("category")
    val category: String? = "",

    @field:SerializedName("quote")
    val quote: String? = ""
) : Parcelable