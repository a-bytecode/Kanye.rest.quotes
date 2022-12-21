package com.example.kanyerestquotes.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kanyerestquotes.data.model.KanyeData
import com.example.kanyerestquotes.data.remote.UserApi

class Repository {


    private val _quotes = MutableLiveData<KanyeData>()
    val quotes : LiveData<KanyeData>
    get() = _quotes


    suspend fun getQuote() {
        val response = UserApi.retrofitService.getQuote()
        Log.d("REPO", "Kanye ${response.quote}")
        _quotes.value = response
    }

}