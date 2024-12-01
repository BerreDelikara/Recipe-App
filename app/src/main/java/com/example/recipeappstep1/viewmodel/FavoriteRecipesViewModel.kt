package com.example.recipeappstep1.viewmodel

import CallApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappstep1.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteRecipesViewModel : ViewModel() {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes


    fun fetchRecipes(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeList = CallApi().parseAllRecipesInCategory(categoryName)

            withContext(Dispatchers.Main) {
                _recipes.value = recipeList
            }
        }
    }
}
