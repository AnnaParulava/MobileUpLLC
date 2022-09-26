package com.example.mobileupllc.fragments

import android.R.layout
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mobileupllc.CellClickListener
import com.example.mobileupllc.R
import com.example.mobileupllc.adapters.CryptocurrenciesRecyclerAdapter
import com.example.mobileupllc.api.Api
import com.example.mobileupllc.model.CryptocurrenciesRecyclerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CryptocurrenciesRecyclerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var cryptocurrenciesRecyclerAdapter: RecyclerView.Adapter<*>
    private var desired_string: String? = null
    private var refreshTimes = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View =
            inflater.inflate(R.layout.fragment_cryptocurrencies_recycler, container, false)
        recyclerView = view.findViewById(R.id.recycler_cryptocurrencies)
        progressBar = view.findViewById(R.id.pb_cryptocurrencies)
        manager = LinearLayoutManager(context)

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CryptocurrenciesRecyclerFragment().apply {
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments
        desired_string = arguments!!.getString("string_key")
        desired_string = "eur"

        getRetrofit(desired_string, 1)

        val itemsSwipeToRefresh = view.findViewById<SwipeRefreshLayout>(R.id.items_swipe_to_refresh)

        itemsSwipeToRefresh.setOnRefreshListener {
            refreshTimes = +refreshTimes + 10
            getRetrofit(desired_string, 2)
            itemsSwipeToRefresh.isRefreshing = false
        }
        }

    private fun customToast(){

        val toastCustom = view?.findViewById<LinearLayout>(R.id.custom_toast_layout)
        val toastLayout = layoutInflater.inflate(R.layout.custom_toast, toastCustom)
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_LONG
        toast.view = toastLayout
        toast.show()

    }


        private fun getRetrofit(crypt: String?, errCode: Int) {
            Api.call.getCryptoList(crypt)
                .enqueue(object : Callback<List<CryptocurrenciesRecyclerModel>> {
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
                            cryptocurrenciesRecyclerAdapter =
                                CryptocurrenciesRecyclerAdapter(
                                    desired_string,
                                    response.body()!!,
                                    object : CellClickListener {
                                        override fun onCellClickListener(cryptocurrenciesTitle: CryptocurrenciesRecyclerModel) {
                                            val arguments = Bundle()
                                            arguments.putString(
                                                "string_key_crypt_title",
                                                cryptocurrenciesTitle.name
                                            )
                                            arguments.putString(
                                                "string_key_crypt_id",
                                                cryptocurrenciesTitle.id
                                            )
                                            findNavController().navigate(
                                                R.id.cryptocurrencyDescriptionFragment,
                                                arguments
                                            )
                                        }
                                    })
                            layoutManager = manager
                            adapter = cryptocurrenciesRecyclerAdapter
                            recyclerView.adapter!!.notifyDataSetChanged()
                            progressBar.visibility = View.INVISIBLE
                        }
                    }


                    override fun onFailure(
                        call: Call<List<CryptocurrenciesRecyclerModel>>,
                        t: Throwable
                    ) {
                        when(errCode){
                            1 -> findNavController().navigate(R.id.errorMessageFragment)
                            2 -> customToast()
                        }
                        t.printStackTrace()
                    }
                })
        }

    }