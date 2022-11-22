package com.example.kotlinnewchatapp.ViewModels

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinnewchatapp.Adapters.ChatFragmentRecyclerViewAdapter
import com.example.kotlinnewchatapp.Models.MessageModel
import com.example.kotlinnewchatapp.Models.UserModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ChatFragmentViewModel : ViewModel() {


    val db = Firebase.firestore
    val ref = db.collection("users")
    val receivedMessageModel = MutableLiveData<ArrayList<MessageModel>>()
    val receivedList = ArrayList<MessageModel>()




    fun getAllMessages () = CoroutineScope(Dispatchers.IO).launch {


        val orderByTime = ref.document("user1").collection("messages").orderBy("time").addSnapshotListener { value, error ->

            receivedList.clear()
            for (i in value!!.documents){

                val receivedSenderName:String = i.get("name").toString()
                val receivedText:String = i.get("text").toString()
                val receivedTime:String = i.get("time").toString()

                println(receivedTime)

                val messageModel = MessageModel(messageText = receivedText, senderName =  receivedSenderName, sendTime = receivedTime)

                receivedList.add(messageModel)

            }

            receivedMessageModel.postValue(receivedList)

        }



    }




    fun sendMessage (currentUserModel:UserModel,messageModel:MessageModel,adapter: ChatFragmentRecyclerViewAdapter,context: Context){


        if (messageModel.messageText == null || messageModel.messageText == ""){
            Toast.makeText(context, "Message box can't be empty!", Toast.LENGTH_SHORT).show()
        }else{
            val haspMap = HashMap<String,Any>()


            val senderName = messageModel.senderName
            val messageText = messageModel.messageText
            val sendingTime = messageModel.sendTime

            haspMap.put("name",senderName!!)
            haspMap.put("text",messageText)
            haspMap.put("time",sendingTime!!)

            val ref_to_messages = ref.document("user1").collection("messages").document().set(haspMap,
                SetOptions.merge()).addOnSuccessListener {
            }


        }





    }



}