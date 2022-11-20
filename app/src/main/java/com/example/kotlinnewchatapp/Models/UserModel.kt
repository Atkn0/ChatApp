package com.example.kotlinnewchatapp.Models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.net.URL


@Parcelize
data class UserModel(
    val imageUrl: URL? = null,
    val userName:String,
    val userSurname:String,
    val lastMessageFromUser:String? = null,
    val lastMessageTime:String? = null,
) : Parcelable
