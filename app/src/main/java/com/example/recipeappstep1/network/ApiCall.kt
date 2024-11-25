package com.example.recipeappstep1.network

import com.example.recipeappstep1.model.Ingredient
import com.example.recipeappstep1.model.Recipe
import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson
import com.google.gson.JsonParser

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"



class ApiCall {

    fun run() : MutableList<Recipe> {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(BASE_URL)
            .build()

        var recipeList = mutableListOf<Recipe>()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw Exception("Unexpected code $response")

            val jsonObject = JsonParser.parseString(response.toString()).asJsonArray
            val jsonArray = jsonObject.getAsJsonArray()

            val gson = Gson()
            val recipes = jsonArray.map { mealJsonElement ->
                val recipeObject = mealJsonElement.asJsonObject

                val recipe = gson.fromJson(recipeObject, Recipe::class.java)

                val ingredients = mutableListOf<Ingredient>()
                for (i in 1..20) {
                    val ingredientName =
                        recipeObject.get("strIngredient$i")?.asString?.takeIf { it.isNotEmpty() }
                    val ingredientMeasure =
                        recipeObject.get("strMeasure$i")?.asString?.takeIf { it.isNotEmpty() }
                    if (ingredientName != null && ingredientMeasure != null) {
                        ingredients.add(
                            Ingredient(
                                name = ingredientName,
                                measure = ingredientMeasure
                            )
                        )
                    }
                }
                recipe.copy(ingredients = ingredients)

            }
            recipeList = recipes.toMutableList()
        }
        return recipeList
    }

}
