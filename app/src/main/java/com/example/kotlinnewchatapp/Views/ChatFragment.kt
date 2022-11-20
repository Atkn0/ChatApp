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
import com.example.kotlinnewchatapp.Models.MessageModel
import com.example.kotlinnewchatapp.Models.UserModel
import com.example.kotlinnewchatapp.ViewModels.ChatFragmentViewModel
import com.example.kotlinnewchatapp.ViewModels.MainActivityViewModel
import com.example.kotlinnewchatapp.databinding.FragmentChatBinding
import java.util.*


class ChatFragment : Fragment() {

    lateinit var binding: FragmentChatBinding
    lateinit var adapter: ChatFragmentRecyclerViewAdapter
    private val args by navArgs<ChatFragmentArgs>()
    val chatViewModel by viewModels<ChatFragmentViewModel>()


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


        binding.SendButtonCardView.setOnClickListener {
            val messageText = binding.MessageEditText.text.toString()
            val senderName = "me"
            val sendTime = Calendar.getInstance().time.toString()

            chatViewModel.sendMessage(args.currentUserData,MessageModel(messageText, senderName, sendTime))

            binding.MessageEditText.text.clear()

        }


        adapter = ChatFragmentRecyclerViewAdapter(arrayListOf())
        binding.chatFragmentRecyclerView.adapter = adapter
        binding.chatFragmentRecyclerView.layoutManager = LinearLayoutManager(context)


        return view
    }






}