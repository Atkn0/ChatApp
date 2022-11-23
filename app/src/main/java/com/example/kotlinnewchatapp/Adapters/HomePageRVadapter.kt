package com.example.kotlinnewchatapp.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinnewchatapp.Models.UserModel
import com.example.kotlinnewchatapp.R
import com.example.kotlinnewchatapp.databinding.ChatListModelLayoutBinding
import com.google.firebase.firestore.auth.User
import com.squareup.picasso.Picasso
import java.net.URI

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
        val currenImageUrl = currentUser.imageUrl
        Picasso.get()
            .load(currenImageUrl)
            .into(holder.binding.UserPhotoImageView)

        holder.binding.LastMessageTextView.text = currentUser.lastMessageText
        holder.binding.LastMessageTimeTextView.text = currentUser.lastMessageTime

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


