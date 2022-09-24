package com.example.mobileupllc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobileupllc.R


class CryptocurrenciesListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addFragmentToFragment(CryptocurrenciesRecyclerFragment.newInstance())
        return inflater.inflate(R.layout.fragment_cryptocurrencies_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CryptocurrenciesListFragment().apply {
            }
    }

    private fun addFragmentToFragment(fragment: Fragment){
        val ft = childFragmentManager.beginTransaction()
        ft.add(R.id.chip_host_fragment, fragment, fragment.javaClass.name)
        ft.commitAllowingStateLoss()
    }
}