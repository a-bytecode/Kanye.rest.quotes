package com.example.kanyerestquotes.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.kanyerestquotes.MainViewModel
import com.example.kanyerestquotes.adapter.QuotesAdapter
import com.example.kanyerestquotes.databinding.ListFragmentBinding

class List_Fragment: Fragment() {

    private lateinit var binding : ListFragmentBinding
    private lateinit var quoteAdapter : QuotesAdapter

    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ListFragmentBinding.inflate(inflater)
        quoteAdapter = QuotesAdapter()
        binding.quotesRecycler.adapter = quoteAdapter

        viewModel.favQuotes.observe(viewLifecycleOwner, Observer {
            if ( it != null) {
                quoteAdapter.submitlist(it)
                Log.d("FAVQUOTE", "FOUNDQUOTE: $it")

            }
        })

        viewModel.getAllFav()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding.searchButtonList.setOnClickListener {
            val searchTerm = binding.textInputListFragment.text.toString()

                if (searchTerm.isNotEmpty()) {
                    viewModel.getAllFavByName(searchTerm)
                    Log.d("FOUNDQOUTE", "FOUNDQUOTE: $searchTerm")
                } else {
                    viewModel.getAllFav()
                    Log.d("DATENCHECK","ERHALTEN ${viewModel.getAllFav()}")
                    Toast.makeText(requireContext(),"Bitte Suchbegriff eingeben",
                        Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

