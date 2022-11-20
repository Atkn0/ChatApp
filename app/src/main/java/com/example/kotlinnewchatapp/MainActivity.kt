package com.example.kotlinnewchatapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.kotlinnewchatapp.Models.UserModel
import com.example.kotlinnewchatapp.ViewModels.MainActivityViewModel
import com.example.kotlinnewchatapp.Views.ChatFragment
import com.example.kotlinnewchatapp.Views.HomeFragment
import com.example.kotlinnewchatapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var userList:ArrayList<UserModel>
    private var chatFragment = ChatFragment()
    lateinit var viewModel: MainActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel.toolBarTitleListener.observe(this, Observer {
            binding.ToolBar.title = it
        })

        setContentView(view)


    }








}

