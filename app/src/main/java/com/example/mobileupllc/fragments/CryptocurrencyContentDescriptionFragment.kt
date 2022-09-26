package com.example.mobileupllc.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobileupllc.R
import com.example.mobileupllc.api.Api
import com.example.mobileupllc.model.CryptocurrencyDescriptionModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CryptocurrencyContentDescriptionFragment : Fragment() {

    private lateinit var imgCryptocurrDescription: ImageView
    private lateinit var tvCryptocurrenciesDescriptionTitle: TextView
    private lateinit var tvCryptocurrenciesDescription: TextView
    private lateinit var tvCryptoDescriptionCategoriesTitle: TextView
    private lateinit var tvCryptoDescriptionCategories: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =  inflater.inflate(
            R.layout.fragment_cryptocurrency_content_description,
            container,
            false
        )
        imgCryptocurrDescription = view.findViewById(R.id.imgCryptocurrDescription)
        tvCryptocurrenciesDescriptionTitle = view.findViewById(R.id.tvCryptocurrenciesDescriptionTitle)
        tvCryptocurrenciesDescription = view.findViewById(R.id.tvCryptocurrenciesDescription)
        tvCryptoDescriptionCategoriesTitle = view.findViewById(R.id.tvCryptoDescriptionCategoriesTitle)
        tvCryptoDescriptionCategories = view.findViewById(R.id.tvCryptoDescriptionCategories)
        progressBar = view.findViewById(R.id.pb_description)

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
        val arguments = arguments
        val cryptId = arguments?.getString("description_key_crypt")
        getRetrofit(cryptId)
    }

    private fun getRetrofit(cryptId: String?) {
        Api.call.getCryptoDescription(cryptId)
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

                    progressBar.visibility = View.INVISIBLE

                    val responseBody = response.body()!!
                    val description =  responseBody.description.toString().replace("Description(en=", "")
                    val categories = responseBody.categories.toString().replace("[", "").replace("]", "")
                    val image = responseBody.image.toString().replace(")", "").replace("Image(large=", "")


                    Picasso.get()
                        .load(image)
                        .into(imgCryptocurrDescription)

                    tvCryptocurrenciesDescriptionTitle.text = activity!!.getString(R.string.description)
                    tvCryptocurrenciesDescription.text = description
                    tvCryptoDescriptionCategoriesTitle.text = activity!!.getString(R.string.categories)
                    tvCryptoDescriptionCategories.text = categories

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