package com.example.kotlinnewchatapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinnewchatapp.Models.MessageModel
import com.example.kotlinnewchatapp.databinding.MessageChatLayoutBinding

class ChatFragmentRecyclerViewAdapter(messageList:ArrayList<MessageModel>) : RecyclerView.Adapter<ChatFragmentRecyclerViewAdapter.myHolder>() {


    class myHolder(val binding:MessageChatLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        val binding = MessageChatLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  myHolder(binding)
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.binding.chatBoxLayout
    }

    override fun getItemCount(): Int {
        return 5
    }
}