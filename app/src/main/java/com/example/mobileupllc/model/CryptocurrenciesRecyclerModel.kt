package com.example.mobileupllc.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CryptocurrenciesRecyclerModel {
    @SerializedName("id")
    @Expose
    private val id: String? = null

    @SerializedName("symbol")
    @Expose
    private val symbol: String? = null

    @SerializedName("name")
    @Expose
    private val name: String? = null

    @SerializedName("current_price")
    @Expose
    private val current_price: Double? = null

    @SerializedName("price_change_percentage_24h")
    @Expose
    private val price_change_percentage_24h: Double? = null



}