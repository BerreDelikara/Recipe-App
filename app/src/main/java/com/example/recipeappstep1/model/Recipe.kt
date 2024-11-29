package com.example.recipeappstep1.model

import java.io.Serializable

class Recipe(
    val strMeal: String,
    var ingredients: MutableList<Ingredient>?,
    val strInstructions: String?,
    val strCategory: String?,
    val strMealThumb: String,
    val idMeal: Int
) : Serializable {
    fun getIngredients(): String {
        var ingredientsString = ""
        for (ingredient in ingredients!!) {
            if (!ingredient.toString().contains("null"))
                ingredientsString = "$ingredientsString$ingredient\n"
        }

        return ingredientsString
    }

}
