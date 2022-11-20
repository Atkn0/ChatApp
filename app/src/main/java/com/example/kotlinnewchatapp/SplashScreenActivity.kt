package com.example.kotlinnewchatapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinnewchatapp.Models.UserModel
import com.example.kotlinnewchatapp.Views.HomeFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.remote.Datastore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.io.Serializable

class SplashScreenActivity: AppCompatActivity() {

    val db = Firebase.firestore
    val userList = ArrayList<UserModel>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        runBlocking {
            launch {
                val firstGet = db.collection("users").get().await()
                val firstUserList = firstGet.documents
                for (i in firstUserList){

                    val name = i.data?.get("name") as String
                    val surname = i.data?.get("surname") as String
                    userList.add(UserModel(userName = name, userSurname = surname))



                }

            }
        }
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()


    }

}


