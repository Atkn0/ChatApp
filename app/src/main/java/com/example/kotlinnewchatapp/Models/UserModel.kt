package com.example.kotlinnewchatapp.Models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.net.URL


@Parcelize
data class UserModel(
    val imageUrl: String?  = null,
    val userName:String,
    val userSurname:String,
    val lastMessageText:String? = null,
    val lastMessageTime:String? = null,
    val userId:String? = null,
) : Parcelable
