package com.example.kotlinnewchatapp.ViewModels

import android.text.Editable
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

class ChatFragmentViewModel : ViewModel() {


    val db = Firebase.firestore
    val ref = db.collection("users")
    val receivedMessageModel = MutableLiveData<ArrayList<MessageModel>>()
    val receivedList = ArrayList<MessageModel>()



    fun getAllMessages () = CoroutineScope(Dispatchers.IO).launch {

        val allMessage = ref.document("user1").collection("messages").get().addOnCompleteListener {

            if(it.isSuccessful){
                for (i in it.result.documents){

                    val a = i.data
                    if (a == null){
                        println("a null döndü")
                    }else{

                        val receivedSenderName:String = a.get("name").toString()
                        val receivedText:String = a.get("text").toString()
                        val receivedTime:String = a.get("time").toString()

                        val timeStamp = Timestamp.now().seconds
                        println(timeStamp)



                        val messageModel = MessageModel(messageText = receivedText, senderName =  receivedSenderName, sendTime = receivedTime)
                        println(messageModel)

                        receivedList.add(messageModel)

                    }



                }

                receivedMessageModel.postValue(receivedList)


            }else{
                println("başarılı dönmedi")
            }
        }


    }




    fun sendMessage (currentUserModel:UserModel,messageModel:MessageModel,adapter: ChatFragmentRecyclerViewAdapter){


        val haspMap = HashMap<String,Any>()


        val senderName = messageModel.senderName
        val messageText = messageModel.messageText
        val sendingTime = messageModel.sendTime

        haspMap.put("name",senderName!!)
        haspMap.put("text",messageText!!)
        haspMap.put("time",sendingTime!!)

        val ref_to_messages = ref.document("user1").collection("messages").document().set(haspMap,
            SetOptions.merge()).addOnSuccessListener {
        }




    }



}