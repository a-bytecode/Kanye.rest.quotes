package com.example.kanyerestquotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.kanyerestquotes.MainViewModel
import com.example.kanyerestquotes.data.model.KanyeData
import com.example.kanyerestquotes.databinding.DetailFragmentBinding

class DetailFragment: Fragment() {

    private lateinit var binding: DetailFragmentBinding
    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailFragmentBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val quoteId = requireArguments().getInt("quoteId")

        val quote = viewModel.quotesList.value?.get(quoteId)

        binding.quotesTextDetail.text = quote!!.quote
    }
}