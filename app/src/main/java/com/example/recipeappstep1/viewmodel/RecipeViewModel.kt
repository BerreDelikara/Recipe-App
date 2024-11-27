package com.example.recipeappstep1.viewmodel

import Parser
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappstep1.model.Recipe
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application){

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe> get() = _recipe

    /**
     * Fetch recipe details by ID or name (depending on your API/repository logic)
     */
    fun fetchRecipe(recipeName: String, categoryName: String) {
        viewModelScope.launch {
            val context = getApplication<Application>()
            try {
                val fetchedRecipe = Parser().parseCategories(context)
                _recipe.postValue(Recipe("ya",null,"ya","ya","ya",10))
            } catch (e: Exception) {
                // Handle exceptions
                e.printStackTrace()
            }
        }
    }
}
