package com.example.kanyerestquotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kanyerestquotes.R
import com.example.kanyerestquotes.databinding.SplashscreenFragmentBinding

class SplashFragment: Fragment() {

    private lateinit var binding: SplashscreenFragmentBinding

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