package com.example.kanyerestquotes

import android.animation.ObjectAnimator
import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kanyerestquotes.data.Repository
import com.example.kanyerestquotes.data.model.KanyeData
import com.example.kanyerestquotes.local.QuoteDatabase
import com.example.kanyerestquotes.local.getDatabase
import kotlinx.coroutines.launch


class MainViewModel(application:Application) : AndroidViewModel(application) {


    val database = getDatabase(application)

    val repository = Repository(database)

    // TODO hier werden die quotes ausn repository durchgespeist

    val quote = repository.quote

    val quotesList = repository.quotes


    fun buttonAnimator(button: Button) {
        // animatorTwo verändert ROTATION_X (X-Achse) von RotateButton laufend von 0f bis 360f
        // innerhalb 2000ms
        val animatorTwo = ObjectAnimator.ofFloat(button, View.ROTATION_X, 0f, 360f)
        animatorTwo.duration = 500
        animatorTwo.start()
    }

    fun getQuote() {
        viewModelScope.launch {
            try {
                repository.getQuote()
            } catch (e:Exception) {
                Log.e("MainViewModel","$e")

            }
        }
    }

    fun getAllFavByName(name:String){
        viewModelScope.launch {
            repository.getAllFavByName(name)
        }
    }

    fun search(term:String) {

        viewModelScope.launch {
            try {

            } catch (e:Exception) {

            }
        }

    }

}