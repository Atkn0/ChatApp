package com.example.kotlinnewchatapp.Models

import java.net.URL

data class UserModel(
    val imageUrl: URL? = null,
    val userName:String,
    val userSurname:String,
    val lastMessageFromUser:String? = null,
    val lastMessageTime:String? = null,
)
