package com.example.recipeappstep1.model

import java.io.Serializable

data class Recipe (
    val recipeName:String, val recipeIngredients: List<String>, val recipeInstructions:String, val recipeImage:String, val recipeTags: List<String>):
    Serializable

