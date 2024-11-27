package com.example.recipeappstep1.viewmodel
import Parser
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeappstep1.model.Category

class LoginViewModel : ViewModel() {
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: MutableLiveData<Boolean> get() = _isLoggedIn

    fun login(email: String, password: String) {
        if (email == "user@gmail.com" && password == "password") {
            _isLoggedIn.value = true
        } else {
            _isLoggedIn.value = false
        }
    }
}
