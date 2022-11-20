package com.example.kotlinnewchatapp.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinnewchatapp.Adapters.ChatFragmentRecyclerViewAdapter
import com.example.kotlinnewchatapp.Models.UserModel
import com.example.kotlinnewchatapp.ViewModels.MainActivityViewModel
import com.example.kotlinnewchatapp.databinding.FragmentChatBinding


class ChatFragment : Fragment() {

    var listener : ((UserModel) -> Unit)?= null

    lateinit var binding: FragmentChatBinding
    lateinit var adapter: ChatFragmentRecyclerViewAdapter
    private val args by navArgs<ChatFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val mainViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        mainViewModel.changeTitle(args.currentUserData.userName)

        activity?.onBackPressedDispatcher?.addCallback(this,object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                isEnabled = false

                mainViewModel.changeTitle("1 new message")

                activity?.onBackPressed()
            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChatBinding.inflate(inflater)
        val view = binding.root

        adapter = ChatFragmentRecyclerViewAdapter()
        binding.RecyclerView.adapter = adapter
        binding.RecyclerView.layoutManager = LinearLayoutManager(context)


        return view
    }






}