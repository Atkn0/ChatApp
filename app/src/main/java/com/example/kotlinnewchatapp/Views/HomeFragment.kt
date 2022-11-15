package com.example.kotlinnewchatapp.Views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinnewchatapp.Adapters.HomePageRVadapter
import com.example.kotlinnewchatapp.Models.UserModel
import com.example.kotlinnewchatapp.R
import com.example.kotlinnewchatapp.ViewModels.HomeFragmentViewModel
import com.example.kotlinnewchatapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.*


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    private lateinit var adapter:HomePageRVadapter
    val viewModel by viewModels<HomeFragmentViewModel>()
    var firstList = ArrayList<UserModel>()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAllUsers()





    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        val view = binding.root

        adapter = HomePageRVadapter(firstList)
        binding.HomePageRecyclerView.adapter = adapter
        binding.HomePageRecyclerView.layoutManager = LinearLayoutManager(context)



        return view
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.arda.observe(viewLifecycleOwner, Observer {

            adapter = HomePageRVadapter(it)
            adapter.notifyDataSetChanged()

        })


    }



}