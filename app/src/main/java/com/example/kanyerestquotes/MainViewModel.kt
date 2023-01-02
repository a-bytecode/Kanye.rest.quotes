package com.example.kanyerestquotes

import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kanyerestquotes.data.Repository
import com.example.kanyerestquotes.data.model.KanyeData
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {


    val repository = Repository()

    val quote = repository.quote

    val _quotesList = MutableLiveData<MutableList<KanyeData>>()
            var quotesList = MutableLiveData<MutableList<KanyeData>>()
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
                val response = repository.quote
                Log.d("MainViewModel","Attempting to write in the list")
                response.let{

                    _quotesList.value?.add(it.value!!)
                    Log.d("MainViewModel","writing to the list")}
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