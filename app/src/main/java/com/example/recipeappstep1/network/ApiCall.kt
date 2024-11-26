package com.example.recipeappstep1.network

import com.example.recipeappstep1.model.Category
import com.example.recipeappstep1.model.Ingredient
import com.example.recipeappstep1.model.Recipe
import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson
import com.google.gson.JsonParser

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

class ApiCall {

    fun searchCategory(key: String) : MutableList<Recipe> {
        val client = OkHttpClient()
        val url = BASE_URL + "filter.php?c=" + key
        val request = Request.Builder()
            .url(url)
            .build()

        var recipeList = mutableListOf<Recipe>()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw Exception("Unexpected code $response")
            val jsonObject = JsonParser.parseString(response.toString()).asJsonArray
            val jsonArray = jsonObject.getAsJsonArray()

            val gson = Gson()
            jsonArray.forEach { element ->
                val recipeObject = element.asJsonObject
                val recipe = gson.fromJson(recipeObject, Recipe::class.java)
                for (i in 1..20) {
                    val ingredientName =
                        recipeObject.get("strIngredient$i")?.asString?.takeIf { it.isNotEmpty() }
                    val ingredientMeasure =
                        recipeObject.get("strMeasure$i")?.asString?.takeIf { it.isNotEmpty() }
                    if (ingredientName != null && ingredientMeasure != null) {
                        recipe.ingredients = mutableListOf(Ingredient(ingredientName, ingredientMeasure))
                    }
                }
                recipeList.add(recipe)
            }
        }
        return recipeList
    }

    fun getCategories(): MutableList<Category> {
        val client = OkHttpClient()
        val url = BASE_URL + "list.php?c=list"
        val request = Request.Builder()
            .url(url)
            .build()

        var categoryList = mutableListOf<Category>()


        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw Exception("Unexpected code $response")

            val jsonObject = JsonParser.parseString(response.toString()).asJsonArray
            val jsonArray = jsonObject.getAsJsonArray()

            val gson = Gson()
            jsonArray.forEach { element ->
                val categoryObject = element.asJsonObject
                val category = gson.fromJson(categoryObject, Category::class.java)
                categoryList.add(category)
            }
        }
        return categoryList
    }

}
