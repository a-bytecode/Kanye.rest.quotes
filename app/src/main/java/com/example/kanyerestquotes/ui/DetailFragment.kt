package com.example.kanyerestquotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kanyerestquotes.databinding.DetailFragmentBinding

class DetailFragment: Fragment() {

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val quoteId = requireArguments().getString("quoteId")

        binding.quotesTextDetail.text = quoteId

        binding.homeButton.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToSplashFragment())
        }
    }
}