package com.example.kanyerestquotes.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.kanyerestquotes.MainViewModel
import com.example.kanyerestquotes.data.Repository
import com.example.kanyerestquotes.data.model.KanyeData
import com.example.kanyerestquotes.databinding.HomeFragmentBinding

class HomeFragment: Fragment() {

    private lateinit var binding: HomeFragmentBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = HomeFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.refreshButton.setOnClickListener {
            viewModel.getQuote()
            viewModel.buttonAnimator(binding.refreshButton)
        }

        viewModel.quotes.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.quotesTextHome.setText(it.quote)
            }
        })


    }
}