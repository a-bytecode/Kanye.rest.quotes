package com.example.kanyerestquotes.data.model

import androidx.room.Entity
import com.squareup.moshi.Json


@Entity
data class KanyeData(

    @Json(name = "quote")
    var quote: String)

