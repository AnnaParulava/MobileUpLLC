package com.example.mobileupllc.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CryptocurrencyDescriptionModel(
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("symbol")
    @Expose
    val symbol: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("description")
    @Expose
    val description: Description,

    @SerializedName("image")
    @Expose
    val image: Image,

    @SerializedName("categories")
    @Expose
    val categories: List<String>
)

data class Description(
    @SerializedName("en")
    @Expose
    val en: String
)

data class Image(
    @SerializedName("large")
    @Expose
    val large: String
)