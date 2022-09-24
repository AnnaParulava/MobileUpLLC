package com.example.mobileupllc.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileupllc.R
import com.example.mobileupllc.adapters.CryptocurrenciesRecyclerAdapter
import com.example.mobileupllc.api.Api
import com.example.mobileupllc.api.JsonAPI

import com.example.mobileupllc.model.CryptocurrenciesRecyclerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CryptocurrenciesRecyclerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var cryptocurrenciesRecyclerAdapter: RecyclerView.Adapter<*>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_cryptocurrencies_recycler, container, false)
        recyclerView = view.findViewById(R.id.recycler_cryptocurrencies)
        manager = LinearLayoutManager(context)
        getRetrofit()
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CryptocurrenciesRecyclerFragment().apply {

            }
    }


    private fun getRetrofit() {


        Api.call.getCryptoList().enqueue(object : Callback<List<CryptocurrenciesRecyclerModel>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<CryptocurrenciesRecyclerModel>>,
                response: Response<List<CryptocurrenciesRecyclerModel>>
            ) {
                if (!response.isSuccessful) {
                    Log.e(
                        "CryptocurrenciesRecycler error",
                        "response " + response.code() + response.body()
                    )
                    return
                }
                Log.d("CryptocurrenciesRecycler ", "response " + response.body())

                recyclerView.apply {
                    cryptocurrenciesRecyclerAdapter = CryptocurrenciesRecyclerAdapter(response.body()!!)
                    layoutManager = manager
                    adapter = cryptocurrenciesRecyclerAdapter
                    //recyclerView.adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<CryptocurrenciesRecyclerModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}