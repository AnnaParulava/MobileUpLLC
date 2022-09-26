package com.example.mobileupllc.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileupllc.CellClickListener
import com.example.mobileupllc.R
import com.example.mobileupllc.model.CryptocurrenciesRecyclerModel
import com.squareup.picasso.Picasso
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.math.roundToInt

class CryptocurrenciesRecyclerAdapter(
    private val symb: String?, private val cryptocurrenciesList: List<CryptocurrenciesRecyclerModel>, private val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<CryptocurrenciesRecyclerAdapter.CryptocurrenciesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrenciesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cryptocurrencies_row_item, parent, false)

        return CryptocurrenciesViewHolder(symb, itemView)
    }

    override fun onBindViewHolder(holder: CryptocurrenciesViewHolder, position: Int) {
        holder.bind(cryptocurrenciesList[position])
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(cryptocurrenciesList[position])
        }
    }

    override fun getItemCount(): Int = cryptocurrenciesList.size

    class CryptocurrenciesViewHolder(private val symb: String?, itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCryptocurrIcon: ImageView = itemView.findViewById(R.id.cryptocurr_icon)
        private val tvCurrentTitle: TextView = itemView.findViewById(R.id.cryptocurr_title)
        private val tvCryptocurrSymbol: TextView = itemView.findViewById(R.id.cryptocurr_symbol)
        private val tvCurrentPrice: TextView = itemView.findViewById(R.id.cryptocurr_current_price)
        private val tvChangePercentage: TextView = itemView.findViewById(R.id.cryptocurr_change_percentage)

        fun getSymb(): String =
              when (symb?.uppercase()) {
                "USD" ->  "$"
                "EUR" ->  "€"
                  else -> ""
              }

        @SuppressLint("SetTextI18n")
        fun bind(cryptocurrencies: CryptocurrenciesRecyclerModel) {

            val percent: Double = cryptocurrencies.price_change_percentage_24h
            if(percent<0)  tvChangePercentage.setTextColor(Color.RED)
            else  tvChangePercentage.setTextColor(Color.parseColor("#2A9D8F"))
            val dformat = DecimalFormat("#.##")
            dformat.roundingMode = RoundingMode.DOWN
            val resultFormat = dformat.format(percent).replace(',', '.')

            val resultFormatPrice = "%,.2f".format(Locale.ENGLISH,cryptocurrencies.current_price)

                tvCurrentTitle.text =  cryptocurrencies.name
            tvCryptocurrSymbol.text = cryptocurrencies.symbol.uppercase(Locale.getDefault())
            tvCurrentPrice.text = getSymb() + " " +resultFormatPrice
            tvChangePercentage.text = "$resultFormat%"

            Picasso.get()
                .load(cryptocurrencies.image)
                .into(tvCryptocurrIcon)
        }

    }



}