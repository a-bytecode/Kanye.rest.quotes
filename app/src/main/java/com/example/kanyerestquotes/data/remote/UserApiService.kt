package com.example.kanyerestquotes.data.remote

import androidx.lifecycle.LiveData
import com.example.kanyerestquotes.data.model.KanyeData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

const val BASE_URl = "https://api.kanye.rest"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URl)
    .build()


interface UserApiService {
    @GET("https://api.kanye.rest")
    suspend fun getQuote(): LiveData<KanyeData>
}

object UserApi {
    val retrofitService: UserApiService by lazy { retrofit.create(UserApiService::class.java) }
}
