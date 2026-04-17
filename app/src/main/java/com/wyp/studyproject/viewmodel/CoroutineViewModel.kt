package com.wyp.studyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CoroutineViewModel: ViewModel() {
    val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    fun getUser() {
        viewModelScope.launch {
            delay(3000)
            _userLiveData.value = User("www",20)
            delay(3000)
            _userLiveData.value = User("yyy",25)
            delay(3000)
            _userLiveData.value = User("ppp",26)
        }
    }






}



data class User(
    val name:String,
    val age: Int
)



