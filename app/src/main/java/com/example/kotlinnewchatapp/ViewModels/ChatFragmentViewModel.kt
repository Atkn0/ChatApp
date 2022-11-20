package com.example.kotlinnewchatapp.ViewModels

import androidx.lifecycle.ViewModel
import com.example.kotlinnewchatapp.Models.MessageModel
import com.example.kotlinnewchatapp.Models.UserModel
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ChatFragmentViewModel : ViewModel() {


    val db = Firebase.firestore
    val ref = db.collection("users")

    fun getAllMessages () = CoroutineScope(Dispatchers.IO).launch {

    }




    fun sendMessage (currentUserModel:UserModel,messageModel:MessageModel){


        val haspMap = HashMap<String,String>()


        val senderName = messageModel.senderName
        val messageText = messageModel.messageText
        val sendingTime = messageModel.sendTime

        haspMap.put("name",senderName)
        haspMap.put("text",messageText)
        haspMap.put("time",sendingTime)

        val ref_to_messages = ref.document("user1").collection("messages").document().set(haspMap,
            SetOptions.merge())




    }



}