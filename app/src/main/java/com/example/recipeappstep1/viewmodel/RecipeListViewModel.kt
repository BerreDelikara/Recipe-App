package com.example.recipeappstep1.viewmodel

import Parser
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeappstep1.model.Recipe

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes


    fun fetchRecipes(categoryName: String) {
        val context = getApplication<Application>()
        try {
            val recipeList = Parser().parseAllRecipesInCategory(categoryName, context)
            _recipes.postValue(recipeList)
        } catch (e: Exception) {
            e.printStackTrace()
            _recipes.postValue(emptyList())
        }
    }
}
