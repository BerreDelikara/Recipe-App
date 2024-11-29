package com.example.recipeappstep1.viewmodel

import CallApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappstep1.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories


    fun fetchCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val categoryList = CallApi().parseCategories()

            withContext(Dispatchers.Main) {
                _categories.value = categoryList
            }
        }
    }
}