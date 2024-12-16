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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Recipe) return false

        return strMeal == other.strMeal &&
                strMealThumb == other.strMealThumb &&
                idMeal == other.idMeal &&
                strCategory == other.strCategory &&
                strInstructions == other.strInstructions &&
                ingredients == other.ingredients
    }


}
