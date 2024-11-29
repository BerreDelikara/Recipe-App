package com.example.recipeappstep1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: MutableLiveData<Boolean> get() = _isLoggedIn

    fun login(email: String, password: String) {
        _isLoggedIn.value = email == "user" && password == "password"
    }
}
