package com.example.recipeappstep1.model

import java.io.Serializable

data class Recipe(
    val strMeal:String, var ingredients: MutableList<Ingredient>, val strInstructions:String, val strCategory:String, val strMealThumb:String,
    val idMeal:Int):
    Serializable

