package com.example.kanyerestquotes

import android.animation.ObjectAnimator
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.ViewModel



class MainViewModel: ViewModel() {


    fun buttonAnimator(button: Button) {
        // animatorTwo verändert ROTATION_X (X-Achse) von RotateButton laufend von 0f bis 360f
        // innerhalb 2000ms
        val animatorTwo = ObjectAnimator.ofFloat(button, View.ROTATION_X, 0f, 360f)
        animatorTwo.duration = 500
        animatorTwo.start()
    }

}