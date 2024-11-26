package com.example.recipeappstep1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeappstep1.model.Recipe
import com.example.recipeappstep1.network.ApiCall

class RecipeViewModel : ViewModel() {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        try {
            val recipeList = ApiCall().searchCategory()
            _recipes.postValue(recipeList)
        } catch (e: Exception) {
            e.printStackTrace()
            _recipes.postValue(emptyList())
        }
    }
}
