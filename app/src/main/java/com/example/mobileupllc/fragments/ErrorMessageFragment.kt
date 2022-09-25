package com.example.mobileupllc.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.mobileupllc.R

class ErrorMessageFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            ErrorMessageFragment().apply {
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_error_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnError = view.findViewById<Button>(R.id.btnError)
        btnError.setOnClickListener{
            findNavController().navigate(R.id.cryptocurrenciesListFragment)
        }
    }
}