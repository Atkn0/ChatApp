package com.example.kotlinnewchatapp.Views

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinnewchatapp.Adapters.ChatFragmentRecyclerViewAdapter
import com.example.kotlinnewchatapp.Models.MessageModel
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

        test()

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


        adapter = ChatFragmentRecyclerViewAdapter(arrayListOf())
        binding.chatFragmentRecyclerView.adapter = adapter
        binding.chatFragmentRecyclerView.layoutManager = LinearLayoutManager(context)


        binding.SendButtonCardView.setOnClickListener {
            val messageText = binding.MessageEditText.text.toString()
            val senderName = "me"
            val sendTime = Calendar.getInstance().time.toString()
            val createdModel = MessageModel(messageText, senderName, sendTime)


            chatViewModel.sendMessage(args.currentUserData,createdModel,adapter)

            binding.MessageEditText.text.clear()

        }


        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatViewModel.receivedMessageModel.observe(viewLifecycleOwner, Observer{

           adapter.updateAdapter(it)
            adapter.notifyDataSetChanged()


        })

    }

    fun test (){
        chatViewModel.getAllMessages()
    }




}