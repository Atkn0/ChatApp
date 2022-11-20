package com.example.kotlinnewchatapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinnewchatapp.Models.UserModel
import com.example.kotlinnewchatapp.databinding.ChatListModelLayoutBinding
import com.google.firebase.firestore.auth.User

class HomePageRVadapter (val userList:ArrayList<UserModel>): RecyclerView.Adapter<HomePageRVadapter.myClassHolder>() {


    lateinit var currentUser:UserModel

    var itemOnClick: ((UserModel) -> Unit)? = null



    class myClassHolder(val binding: ChatListModelLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myClassHolder {
        val binding = ChatListModelLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myClassHolder(binding)
    }

    override fun onBindViewHolder(holder: myClassHolder, position: Int) {

        currentUser = userList[position]

        holder.binding.UserNameTextView.text = currentUser?.userName

        holder.itemView.setOnClickListener {
            val secondCurrent = userList[position]
            itemOnClick?.invoke(secondCurrent)
        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }




    fun updateAdapter(newData: ArrayList<UserModel>){

        userList.clear()

        userList.addAll(newData)

        notifyDataSetChanged()

    }



}


