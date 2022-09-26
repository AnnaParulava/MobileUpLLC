package com.example.mobileupllc.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobileupllc.R
import com.example.mobileupllc.api.Api
import com.example.mobileupllc.model.CryptocurrencyDescriptionModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CryptocurrencyContentDescriptionFragment : Fragment() {

//    private lateinit var imgCryptocurrDescription: ImageView
//    private lateinit var tvCryptocurrenciesDescription: TextView
//    private lateinit var tvCryptocurrenciesDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =  inflater.inflate(
            R.layout.fragment_cryptocurrency_content_description,
            container,
            false
        )
//        tvCryptocurrenciesDescription = view.findViewById(R.id.imgCryptocurrDescription)
//        tvCryptocurrenciesDescription = view.findViewById(R.id.tvCryptocurrenciesDescription)
//        tvCryptocurrenciesDescription = view.findViewById(R.id.tvCryptocurrenciesDescription)
            return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CryptocurrencyContentDescriptionFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRetrofit()
    }

    private fun getRetrofit() {
        Api.call.getCryptoDescription("bitcoin")
            .enqueue(object : Callback<CryptocurrencyDescriptionModel> {
                override fun onResponse(
                    call: Call<CryptocurrencyDescriptionModel>,
                    response: Response<CryptocurrencyDescriptionModel>
                ) {
                    if (!response.isSuccessful) {
                        Log.e(
                            "CryptocurrencyDescriptionModel error",
                            "response " + response.code() + response.body()
                        )
                        return
                    }
                    Log.d("CryptocurrencyDescriptionModel ", "response " + response.body())

                    val responseBody = response.body()!!
                    val description =  responseBody.description.toString().replace("Description(en=", "")
                    tvCryptocurrenciesDescription.text = description

                }


                override fun onFailure(
                    call: Call<CryptocurrencyDescriptionModel>,
                    t: Throwable
                ) {
                    findNavController().navigate(R.id.errorMessageFragment)
                    t.printStackTrace()
                }
            })
    }
}