package com.example.kanyerestquotes

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kanyerestquotes.data.Repository
import com.example.kanyerestquotes.data.model.KanyeData
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {


    val repository = Repository()

    val quotes = repository.quotes

    val _quotesList = MutableLiveData<List<KanyeData>>()
            var quotesList = MutableLiveData<List<KanyeData>>()
            get() = _quotesList


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

    fun search(term:String) {

        viewModelScope.launch {

            try {
                repository.getQuote()

            } catch (e:Exception) {

            }
        }

    }

}