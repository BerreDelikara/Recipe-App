package com.example.recipeappstep1.network

import com.example.recipeappstep1.model.Recipe
import retrofit.Call
import retrofit.http.GET

interface ApiService {
    @GET("search.php?f=a")

    fun getRecipes(): Call<Recipe>
}