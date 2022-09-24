package com.example.mobileupllc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileupllc.R
import com.example.mobileupllc.model.CryptocurrenciesRecyclerModel
import com.squareup.picasso.Picasso

class CryptocurrenciesRecyclerAdapter(private val cryptocurrenciesList: List<CryptocurrenciesRecyclerModel>) :
    RecyclerView.Adapter<CryptocurrenciesRecyclerAdapter.CryptocurrenciesViewHolder>() {

//    fun updateCryptocurrenciesList(newCryptocurrenciesList: List<CryptocurrenciesRecyclerModel>) {
//        cryptocurrenciesList.clear()
//        cryptocurrenciesList.addAll(newCryptocurrenciesList)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrenciesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cryptocurrencies_row_item, parent, false)
        return CryptocurrenciesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CryptocurrenciesViewHolder, position: Int) {
        holder.bind(cryptocurrenciesList[position])
    }

    override fun getItemCount(): Int = cryptocurrenciesList.size

    class CryptocurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCryptocurrIcon: ImageView = itemView.findViewById(R.id.cryptocurr_icon)
        private val tvCurrentTitle: TextView = itemView.findViewById(R.id.cryptocurr_title)
        private val tvCryptocurrSymbol: TextView = itemView.findViewById(R.id.cryptocurr_symbol)
        private val tvCurrentPrice: TextView = itemView.findViewById(R.id.cryptocurr_current_price)
        private val tvChangePercentage: TextView = itemView.findViewById(R.id.cryptocurr_change_percentage)

        fun bind(cryptocurrencies: CryptocurrenciesRecyclerModel) {
            tvCurrentTitle.text = cryptocurrencies.name
            tvCryptocurrSymbol.text = cryptocurrencies.symbol
            tvCurrentPrice.text = cryptocurrencies.current_price.toString()
            tvChangePercentage.text = cryptocurrencies.price_change_percentage_24h.toString()

            Picasso.get()
                .load(cryptocurrencies.image)
                .into(tvCryptocurrIcon)
        }
    }

}