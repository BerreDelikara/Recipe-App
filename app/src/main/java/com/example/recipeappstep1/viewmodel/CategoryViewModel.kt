package com.example.recipeappstep1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeappstep1.model.Category
import com.example.recipeappstep1.network.ApiCall

class CategoryViewModel:ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        try {
            val categoryList = ApiCall().getCategories()
            _categories.postValue(categoryList)
        } catch (e: Exception) {
            e.printStackTrace()
            _categories.postValue(emptyList())
        }
    }
}