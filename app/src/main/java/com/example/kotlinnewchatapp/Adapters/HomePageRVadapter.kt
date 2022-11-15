package com.example.kotlinnewchatapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinnewchatapp.Models.UserModel
import com.example.kotlinnewchatapp.databinding.ChatListModelLayoutBinding

class HomePageRVadapter (val userList:ArrayList<UserModel>): RecyclerView.Adapter<HomePageRVadapter.myClassHolder>() {


    class myClassHolder(val binding: ChatListModelLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myClassHolder {
        val binding = ChatListModelLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myClassHolder(binding)
    }

    override fun onBindViewHolder(holder: myClassHolder, position: Int) {

        holder.binding.UserNameTextView.text = userList[position].userName

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}