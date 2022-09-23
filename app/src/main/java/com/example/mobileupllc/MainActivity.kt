package com.example.mobileupllc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobileupllc.fragments.CryptocurrenciesListFragment
import com.example.mobileupllc.fragments.ErrorMessageFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(CryptocurrenciesListFragment.newInstance(), "cryp_fragment")
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.host_fragment, fragment, tag)
        ft.commit()
    }
}