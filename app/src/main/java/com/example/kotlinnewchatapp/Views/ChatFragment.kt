package com.example.kotlinnewchatapp.Views

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinnewchatapp.Adapters.ChatFragmentRecyclerViewAdapter
import com.example.kotlinnewchatapp.Models.MessageModel
import com.example.kotlinnewchatapp.Models.UserModel
import com.example.kotlinnewchatapp.ViewModels.ChatFragmentViewModel
import com.example.kotlinnewchatapp.ViewModels.MainActivityViewModel
import com.example.kotlinnewchatapp.databinding.FragmentChatBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ChatFragment : Fragment() {

    lateinit var binding: FragmentChatBinding
    lateinit var adapter: ChatFragmentRecyclerViewAdapter
    private val args by navArgs<ChatFragmentArgs>()
    val chatViewModel by viewModels<ChatFragmentViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        val mainViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        val currentUserModel = args.currentUserData
        mainViewModel.changeTitle(currentUserModel.userName)

        getMessagesBefore(currentUserModel)

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




        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatViewModel.receivedMessageModel.observe(viewLifecycleOwner, Observer{

           adapter.updateAdapter(it)
            binding.chatFragmentRecyclerView.scrollToPosition(it.size - 1)
            adapter.notifyDataSetChanged()


        })

        binding.SendButtonCardView.setOnClickListener {

            val messageText = binding.MessageEditText.text.toString()
            val senderName = "me"
            val timeForFirebase = Calendar.getInstance().time.toString()


            val currentDateTime = LocalDateTime.now()
            val time = currentDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))

            val createdModel = MessageModel(messageText, senderName, time,timeForFirebase)


            context?.let { it1 ->
                chatViewModel.sendMessage(args.currentUserData,createdModel,adapter,
                    it1
                )
            }

            binding.MessageEditText.text.clear()

        }


    }

    fun getMessagesBefore (currentUserModel:UserModel){
        chatViewModel.getAllMessages(currentUserModel)
    }




}