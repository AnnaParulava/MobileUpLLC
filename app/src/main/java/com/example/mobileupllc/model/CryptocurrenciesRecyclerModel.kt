package com.example.mobileupllc.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CryptocurrenciesRecyclerModel(
    @SerializedName("id")
    @Expose
     val id: String,

    @SerializedName("image")
    @Expose
    val image: String,

    @SerializedName("symbol")
    @Expose
     val symbol: String,

    @SerializedName("name")
    @Expose
     val name: String,

    @SerializedName("current_price")
    @Expose
     val current_price: Double,

    @SerializedName("price_change_percentage_24h")
    @Expose
     val price_change_percentage_24h: Double
)
