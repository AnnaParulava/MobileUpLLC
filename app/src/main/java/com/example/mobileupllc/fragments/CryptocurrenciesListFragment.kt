package com.example.mobileupllc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobileupllc.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class CryptocurrenciesListFragment : Fragment() {

    private lateinit var chipGroup: ChipGroup
    private lateinit var chipUSD: Chip

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_cryptocurrencies_list, container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chipGroup = view.findViewById(R.id.chipGroupListCryp)
        chipUSD = view.findViewById(R.id.chipUSD)

        val fragment = CryptocurrenciesRecyclerFragment.newInstance()
        val arguments = Bundle()

        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            chip?.let { chipView ->
                val fragment1 = CryptocurrenciesRecyclerFragment.newInstance()
                val arguments1 = Bundle()
                arguments1.putString("string_key", chipView.text.toString())
                fragment1.arguments = arguments1
                addFragmentToFragment(fragment1)
            }

        }
        val ids = chipGroup.checkedChipIds
        for (id in ids) {
            val chip = chipGroup.findViewById<Chip>(id!!)
            arguments.putString("string_key", chip.text.toString())
            fragment.arguments = arguments
            addFragmentToFragment(fragment)
        }
    }


    private fun addFragmentToFragment(fragment: Fragment) {
        val ft = childFragmentManager.beginTransaction()
        ft.replace(R.id.chip_host_fragment, fragment, fragment.javaClass.name)
        ft.commitAllowingStateLoss()

    }
}