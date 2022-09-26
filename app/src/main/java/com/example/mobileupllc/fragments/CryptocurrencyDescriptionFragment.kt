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
import com.example.mobileupllc.R

class CryptocurrencyDescriptionFragment : Fragment() {
    private lateinit var imgCryptocurrenciesTitle: ImageView
    private lateinit var tvCryptocurrenciesTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(R.layout.fragment_cryptocurrency_description, container, false)
        tvCryptocurrenciesTitle = view.findViewById(R.id.tvCryptocurrenciesTitle)
        imgCryptocurrenciesTitle = view.findViewById(R.id.imgCryptocurrenciesTitle)

        val arguments = arguments
        val cryptTitle = arguments?.getString("string_key_crypt_title")

        tvCryptocurrenciesTitle.text = cryptTitle

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CryptocurrencyDescriptionFragment().apply {

            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = CryptocurrencyContentDescriptionFragment.newInstance()
        val cryptID = arguments?.getString("string_key_crypt_id")
        val argumentsCrypt = Bundle()
        argumentsCrypt.putString("description_key_crypt", cryptID.toString())

        fragment.arguments = argumentsCrypt
        addFragmentToFragment(fragment)
    }

    private fun addFragmentToFragment(fragment: Fragment) {
        val ft = childFragmentManager.beginTransaction()
        ft.add(R.id.title_host_fragment, fragment, fragment.javaClass.name)
        ft.commit()

    }

}
