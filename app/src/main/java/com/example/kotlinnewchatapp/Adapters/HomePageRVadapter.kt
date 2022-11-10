package com.example.kotlinnewchatapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinnewchatapp.databinding.ChatListModelLayoutBinding

class HomePageRVadapter : RecyclerView.Adapter<HomePageRVadapter.myClassHolder>() {


    class myClassHolder(val binding: ChatListModelLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myClassHolder {
        val binding = ChatListModelLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myClassHolder(binding)
    }

    override fun onBindViewHolder(holder: myClassHolder, position: Int) {
        holder.binding.HomePageUserChatBox


    }

    override fun getItemCount(): Int {
        return 13
    }
}