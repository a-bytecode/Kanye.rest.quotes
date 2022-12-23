package com.example.kanyerestquotes.data.model

import com.squareup.moshi.Json



data class KanyeData(

    @Json(name = "quote")
    val quote: String

)

