package com.example.kanyerestquotes

import android.animation.ObjectAnimator
import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.*
import com.example.kanyerestquotes.data.Repository
import com.example.kanyerestquotes.data.model.KanyeData
import com.example.kanyerestquotes.local.getDatabase
import kotlinx.coroutines.launch


class MainViewModel(application:Application) : AndroidViewModel(application) {

    enum class ApiStatus { NO_RESULT, FOUND_RESULT }

    val database = getDatabase(application)

    val repository = Repository(database)

    // TODO hier werden die quotes ausn repository durchgespeist

    val quote = repository.quote

    val favQuotes = MutableLiveData<List<KanyeData>>(listOf())

    private var _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus : LiveData<ApiStatus>
        get() = _apiStatus

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
            val quotes = database.QuoteDatabaseDao.getAllFavByName(name)
            if (quotes.isEmpty()) {
                _apiStatus.value = ApiStatus.NO_RESULT }
            else {
                _apiStatus.value = ApiStatus.FOUND_RESULT
                favQuotes.postValue(quotes)
            }

        }
    }

    fun getAllFav() {
        database.QuoteDatabaseDao.getAll().observeForever(object : Observer<List<KanyeData>> {
            override fun onChanged(data: List<KanyeData>) {
                favQuotes.value = data
                // Entferne den Observer, um ein Memory Leak zu vermeiden
                database.QuoteDatabaseDao.getAll().removeObserver(this)
            }
        })
    }

    fun setApiStatus(status:ApiStatus){
        _apiStatus.value = status
    }

    fun apiStatus(){
        setApiStatus(ApiStatus.FOUND_RESULT)
    }



}