package com.example.kanyerestquotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.kanyerestquotes.MainViewModel
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

        viewModel.quote.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.quotesTextHome.setText(it.quote)
            }
        })


    }
}