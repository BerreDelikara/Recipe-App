package com.example.recipeappstep1.data

import android.content.Context
import android.widget.Toast
import com.example.recipeappstep1.model.Ingredient
import com.example.recipeappstep1.model.Recipe
import org.json.JSONObject
import retrofit.*

class ApiCall {
    fun getRecipe(context: Context, callback: (Recipe) -> Unit) {

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()

        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)

        val call: Call<Recipe> = service.getRecipes()

        call.enqueue(object : Callback<Recipe> {
            override fun onResponse(response: Response<Recipe>?, retrofit: Retrofit?) {

                if (response!!.isSuccess) {
                    val jsonObject = JSONObject(response.body().toString())

                    var ingredientsList = mutableListOf<Ingredient>()
                    for (i in 1..20) {
                        if (!jsonObject.getString("strIngredient$i").isNullOrEmpty()) {
                            ingredientsList.add(Ingredient(jsonObject.getString("strIngredient$i"), jsonObject.getString("strMeasure$i")))
                        }
                    }
                    val recipe  = Recipe(jsonObject.getString("strMeal"), ingredientsList, jsonObject.getString("strInstructions"), jsonObject.getString("strCategory"), jsonObject.getString("strMealThumb"), jsonObject.getInt("idMeal"), jsonObject.getString("strTags"))
                    callback(recipe)
                }
            }

            override fun onFailure(t: Throwable?) {
                // This method is called when the API request fails.
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
