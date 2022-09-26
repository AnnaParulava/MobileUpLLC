package com.example.mobileupllc.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//data class CryptocurrencyDescriptionModel(
//    val Message: String,
//    val RecordCount: String,
//    val Result: List<DescriptionModel>,
//    val Status: Int
//)
//

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
    val description: Description
)

data class Description (
    @SerializedName("en")
    @Expose
    val en: String
)