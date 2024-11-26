package com.example.recipeappstep1

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isLoggedIn = MutableLiveData<Boolean>()

    fun login() {

        if (username.value == "deniz" && password.value == "berre") {
            isLoggedIn.value = true
        } else {
            isLoggedIn.value = false
        }
    }
}
