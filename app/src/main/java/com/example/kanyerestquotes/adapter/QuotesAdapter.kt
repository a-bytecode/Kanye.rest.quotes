package com.example.kanyerestquotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kanyerestquotes.R
import com.example.kanyerestquotes.data.model.KanyeData
import com.example.kanyerestquotes.ui.List_FragmentDirections

class QuotesAdapter: RecyclerView.Adapter<QuotesAdapter.ItemViewHolder>() {

    private var dataset = listOf<KanyeData>()

    fun submitlist(quoteList: List<KanyeData>) {
        dataset = quoteList
        notifyDataSetChanged()
    }

    class ItemViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val quoteText = view.findViewById<TextView>(R.id.item_quote_text)
        val cardView = view.findViewById<CardView>(R.id.quotes_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.quotes_item,parent,false)
        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val data : KanyeData = dataset[position]
        holder.quoteText.text = data.quote
        holder.cardView.setOnClickListener {
            holder.itemView.findNavController().navigate(List_FragmentDirections.actionListFragmentToDetailFragment(data.quote.toInt()))
        }


    }

    override fun getItemCount(): Int {
        return dataset.size
    }


}