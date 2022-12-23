package com.example.kanyerestquotes.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kanyerestquotes.MainViewModel
import com.example.kanyerestquotes.R
import com.example.kanyerestquotes.databinding.SplashscreenFragmentBinding

class SplashFragment: Fragment() {

    private lateinit var binding: SplashscreenFragmentBinding

    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashscreenFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Glide
            .with(this)
            .load("https://media.giphy.com/media/3o7TKyL1IgyDtHlg6A/giphy.gif")
            .centerCrop()
            .placeholder(R.drawable.kw)
            .into(binding.gifImageSplash)

        binding.startButtonSplash.setOnClickListener {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }

        binding.showQuotesButton.setOnClickListener {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToListFragment())
        }
    }








}