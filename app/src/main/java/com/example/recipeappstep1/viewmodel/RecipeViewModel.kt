package com.example.recipeappstep1.viewmodel

import CallApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappstep1.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeViewModel : ViewModel() {

    private val _recipe = MutableLiveData<Recipe?>()
    val recipe: MutableLiveData<Recipe?> get() = _recipe

    fun fetchRecipe(recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val recipe = CallApi().parseRecipe(recipeId)

            withContext(Dispatchers.Main) {
                _recipe.value = recipe
            }
        }
    }
}
