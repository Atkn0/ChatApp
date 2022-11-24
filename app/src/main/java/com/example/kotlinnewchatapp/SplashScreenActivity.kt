package com.example.kotlinnewchatapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinnewchatapp.Models.UserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class SplashScreenActivity: AppCompatActivity() {

    val db = Firebase.firestore
    val userList = ArrayList<UserModel>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println("SPLASH SCREEN İÇİNDE")

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
        println("SPLASH SCREEN BİTTİ")
        startActivity(intent)
        finish()


    }

}


