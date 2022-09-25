package com.example.mobileupllc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobileupllc.R


class CryptocurrencyContentDescriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_cryptocurrency_content_description,
            container,
            false
        )
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CryptocurrencyContentDescriptionFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}