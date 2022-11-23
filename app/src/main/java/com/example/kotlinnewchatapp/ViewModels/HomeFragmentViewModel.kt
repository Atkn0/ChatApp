package com.example.kotlinnewchatapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinnewchatapp.Models.UserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeFragmentViewModel : ViewModel() {

    val db = Firebase.firestore
    var arda = MutableLiveData<ArrayList<UserModel>>()
    val arraylistUser = ArrayList<UserModel>()


    fun getAllUsers () = CoroutineScope(Dispatchers.IO).launch{

        try {

            val test = db.collection("users").get().await()
            val test2 = test.documents
            for (i in test2){
                val name = i.data?.get("name") as String
                val surname = i.data?.get("surname") as String
                val imageUrl = i.data?.get("imageUrl") as String
                val userID = i.data?.get("userId") as String

                arraylistUser.add(UserModel(userName = name, userSurname = surname, imageUrl = imageUrl, userId = userID))
            }

            arda.postValue(arraylistUser)


        }catch (e:Exception){
            e.printStackTrace()
        }


    }



}