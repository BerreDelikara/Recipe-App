package com.example.recipeappstep1.model

import java.io.Serializable

data class Recipe(
    val name:String, val ingredients: MutableList<Ingredient>, val instructions:String, val category:String, val image:String,
    val id:Int, val tags: String):
    Serializable

