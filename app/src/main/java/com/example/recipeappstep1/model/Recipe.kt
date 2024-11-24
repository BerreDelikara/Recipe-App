package com.example.recipeappstep1.model

import java.io.Serializable

data class Recipe (
    val name:String, val ingredients: List<String>, val instructions:String, val image:String, val tags: List<String>):
    Serializable

