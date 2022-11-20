package com.example.kotlinnewchatapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var toolBarTitleListener = MutableLiveData<String>()

    fun changeTitle (title:String){

        toolBarTitleListener.value = title

    }

}