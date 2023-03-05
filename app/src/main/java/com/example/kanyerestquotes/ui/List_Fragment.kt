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

        // Um das Laden der Liste aus der Datenbank direkt beim starten
        // des Fragments zu gewährleisten,
        // observen und befüllen wir die liste schon direkt aus der onCreatedView.

        binding = ListFragmentBinding.inflate(inflater)
        quoteAdapter = QuotesAdapter()
        binding.quotesRecycler.adapter = quoteAdapter

        viewModel.favQuotes.observe(viewLifecycleOwner, Observer {
            if ( it != null) {
                quoteAdapter.submitlist(it)
                Log.d("FAVQUOTE", "FOUNDQUOTE: $it")

            }
        })
        viewModel.apiStatus() // hiermit setzten wir den ApiStatus auf "FoundResult"
        // damit die Liste bei jedem start des Fragmentes sichtbar wird.
        viewModel.getAllFav() // hiermit befüllen wir die Liste.

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel.apiStatus.observe(viewLifecycleOwner) {


            when(it) {

                MainViewModel.ApiStatus.FOUND_RESULT -> {
                    binding.quotesRecycler.visibility = View.VISIBLE
                    binding.linearLayoutNoResult.visibility = View.GONE
                }

                MainViewModel.ApiStatus.NO_RESULT -> {
                    binding.quotesRecycler.visibility = View.GONE
                    binding.linearLayoutNoResult.visibility = View.VISIBLE
                }
            }
        }

        binding.searchButtonList.setOnClickListener {
            val searchTerm = binding.textInputListFragment.text.toString()

                if (searchTerm.isNotEmpty()) {
                    viewModel.getAllFavByName(searchTerm)
                    Log.d("FOUNDQOUTE", "FOUNDQUOTE: $searchTerm")
                } else {
                    viewModel.getAllFav()
                    binding.quotesRecycler.visibility = View.VISIBLE
                    binding.linearLayoutNoResult.visibility = View.GONE
                    Log.d("DATENCHECK","ERHALTEN ${viewModel.getAllFav()}")
                    Toast.makeText(requireContext(),"Bitte Suchbegriff eingeben",
                        Toast.LENGTH_SHORT).show()
                }



            }

        }

    }

