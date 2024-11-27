package com.example.recipeappstep1.viewmodel

import Parser
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeappstep1.model.Category

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        val context = getApplication<Application>()
        try {
            val categoryList = Parser().parseCategories(context)
            _categories.postValue(categoryList)
        } catch (e: Exception) {
            e.printStackTrace()
            _categories.postValue(emptyList())
        }
    }
}