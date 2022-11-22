package com.example.kotlinnewchatapp.Adapters

import android.annotation.SuppressLint
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinnewchatapp.Models.MessageModel
import com.example.kotlinnewchatapp.databinding.MessageChatLayoutBinding

class ChatFragmentRecyclerViewAdapter(val messageList:ArrayList<MessageModel>) : RecyclerView.Adapter<ChatFragmentRecyclerViewAdapter.myHolder>() {


    class myHolder(val binding:MessageChatLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        val binding = MessageChatLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  myHolder(binding)
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.binding.textInputEditText2.setText(messageList[position].messageText)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter (newData:ArrayList<MessageModel>){

        messageList.clear()
        messageList.addAll(newData)
        notifyDataSetChanged()


    }

}