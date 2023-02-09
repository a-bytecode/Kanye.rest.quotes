package com.example.kanyerestquotes.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kanyerestquotes.data.model.KanyeData
import com.example.kanyerestquotes.data.remote.UserApi
import com.example.kanyerestquotes.local.QuoteDatabase
import com.example.kanyerestquotes.local.QuoteDatabaseDao

class Repository(private val database: QuoteDatabase) {


    private val _quote = MutableLiveData<KanyeData>()
    val quote : LiveData<KanyeData>
    get() = _quote


    val dB = database.QuoteDatabaseDao
    val getFavs = database.QuoteDatabaseDao.getAll()

    suspend fun getAllFavByName(name:String):List<KanyeData> {
        return dB.getAllFavByName(name)
    }

    //TODO die quotes werden aus der Datanbank durchgespeist

    val quotes = database.QuoteDatabaseDao.getAll()

    suspend fun getQuote() {
        val response = UserApi.retrofitService.getQuote()
        Log.d("REPO", "Kanye ${response}")
        database.QuoteDatabaseDao.insert(response)
        _quote.value = response
    }


}