package com.example.kanyerestquotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity
data class KanyeData(

    @PrimaryKey
    @Json(name = "quote")
    var quote: String)

