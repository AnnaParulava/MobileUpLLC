package com.example.mobileupllc.api

import com.example.mobileupllc.model.CryptocurrenciesRecyclerModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.Path

import retrofit2.http.GET

val BASE_URL = "https://api.coingecko.com/api/v3/"

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service: JsonAPI = retrofit.create(JsonAPI::class.java)

object Api {
    val call: JsonAPI by lazy{service}
}
//{vs_currency}
interface JsonAPI {
    @GET("coins/markets?vs_currency=usd")
    fun getCryptoList(): Call<List<CryptocurrenciesRecyclerModel>>
}