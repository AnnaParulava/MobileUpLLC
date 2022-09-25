package com.example.mobileupllc.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mobileupllc.R

class CryptocurrencyDescriptionFragment : Fragment() {
private lateinit var imgCryptocurrenciesTitle: ImageView
private lateinit var tvCryptocurrenciesTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =inflater.inflate(R.layout.fragment_cryptocurrency_description, container, false)
        //      imgCryptocurrenciesTitle.setColorFilter(Color.BLACK)
        val arguments = arguments
        val cryptTitle = arguments?.getString("string_key_crypt")
        tvCryptocurrenciesTitle = view.findViewById(R.id.tvCryptocurrenciesTitle)
        tvCryptocurrenciesTitle.text = cryptTitle
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CryptocurrencyDescriptionFragment().apply {

            }
    }
}
