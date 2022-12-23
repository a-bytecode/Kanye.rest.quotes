package com.example.kanyerestquotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.kanyerestquotes.MainViewModel
import com.example.kanyerestquotes.adapter.QuotesAdapter
import com.example.kanyerestquotes.databinding.ListFragmentBinding

class List_Fragment: Fragment() {



    private lateinit var binding : ListFragmentBinding

    private val viewModel : MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(inflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val quoteAdapter = QuotesAdapter()

        binding.quotesRecycler.adapter = quoteAdapter


        viewModel.quotes.observe(viewLifecycleOwner, Observer {
            quoteAdapter.submitlist(it)
        })







    }
}