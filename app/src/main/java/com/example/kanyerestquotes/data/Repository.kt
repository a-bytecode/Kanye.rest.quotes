package com.example.kanyerestquotes.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kanyerestquotes.data.model.KanyeData
import com.example.kanyerestquotes.data.remote.UserApi
import com.example.kanyerestquotes.local.QuoteDatabase

class Repository(private val database: QuoteDatabase) {


    private val _quote = MutableLiveData<KanyeData>()
    val quote : LiveData<KanyeData>
    get() = _quote


    suspend fun getQuote() {
        val response = UserApi.retrofitService.getQuote()
        Log.d("REPO", "Kanye ${response}")
        database.QuoteDatabaseDao.insert(response)
        _quote.value = response

    }

}