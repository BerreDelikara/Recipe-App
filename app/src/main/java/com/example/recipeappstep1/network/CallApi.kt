import com.example.recipeappstep1.model.Category
import com.example.recipeappstep1.model.Ingredient
import com.example.recipeappstep1.model.Recipe
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class CallApi {

    val baseUrl = "https://themealdb.com/api/json/v1/1/"
    fun makeHttpRequest(key: String): JSONObject? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("$baseUrl$key")
            .build()

        return try {
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val jsonString = response.body?.string()
                if (jsonString != null) {
                    JSONObject(jsonString)
                } else {
                    null
                }
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseAllRecipesInCategory(categoryName: String): List<Recipe> {

        val jsonString = makeHttpRequest("filter.php?c=$categoryName")?.toString()
        val recipesArray = JSONObject(jsonString).getJSONArray("meals")
        val recipes = mutableListOf<Recipe>()

        for (i in 0 until recipesArray.length()) {
            val recipeObject = recipesArray.getJSONObject(i)

            val recipe = Recipe(
                idMeal = recipeObject.optInt("idMeal"),
                strMeal = recipeObject.optString("strMeal"),
                strMealThumb = recipeObject.optString("strMealThumb"),
                ingredients = null,
                strInstructions = null,
                strCategory = null
            )
            recipes.add(recipe)
        }

        return recipes
    }

    fun parseCategories(): List<Category> {
        val jsonString = makeHttpRequest("categories.php")?.toString()

        val categoryList = mutableListOf<Category>()
        val categoriesArray = JSONObject(jsonString).getJSONArray("categories")

        for (i in 0 until categoriesArray.length()) {
            val categoryObject = categoriesArray.getJSONObject(i)

            val category = Category(
                strCategory = categoryObject.getString("strCategory"),
                strCategoryThumb = categoryObject.getString("strCategoryThumb"),
            )

            categoryList.add(category)
        }
        return categoryList
    }

    fun parseRecipe(recipeId: Int): Recipe {
        val jsonString = makeHttpRequest("lookup.php?i=$recipeId")?.toString()

        val recipeArray = JSONObject(jsonString).getJSONArray("meals")
        val recipeObject = recipeArray.getJSONObject(0)

        val ingredientList = mutableListOf<Ingredient>()
        for (j in 1..20) {
            val ingName = recipeObject.optString("strIngredient$j")
            val measure = recipeObject.optString("strMeasure$j")
            if (ingName.isNotEmpty()) {
                ingredientList.add(Ingredient(ingName, measure))
            }
        }

        val recipe = Recipe(
            idMeal = recipeObject.optInt("idMeal"),
            strMeal = recipeObject.optString("strMeal"),
            strMealThumb = recipeObject.optString("strMealThumb"),
            ingredients = ingredientList,
            strInstructions = recipeObject.optString("strInstructions"),
            strCategory = recipeObject.optString("strCategory")
        )

        return recipe

    }

    fun parseAllRecipeIds(recipeIds: List<String>) : List<Recipe> {
        val recipes = mutableListOf<Recipe>()
        for (recipeId in recipeIds) {
            val recipe = parseRecipe(Integer.parseInt(recipeId))
            recipes.add(recipe)
        }
        return recipes
    }

    fun parseSearchResults(searchQuery: String): List<Recipe> {
        val jsonString = makeHttpRequest("search.php?s=$searchQuery")?.toString()

        val recipes = mutableListOf<Recipe>()

        if (jsonString != null) {
            if (jsonString.equals("{\"meals\":null}")) {
                recipes.add(Recipe(idMeal = -1, strMeal = "     No recipes found", strMealThumb = "", ingredients = null, strInstructions = null, strCategory = null))
                return recipes
            }
        }

        val recipesArray = JSONObject(jsonString).getJSONArray("meals")

        for (i in 0 until recipesArray.length()) {
            val recipeObject = recipesArray.getJSONObject(i)

            val recipe = Recipe(
                idMeal = recipeObject.optInt("idMeal"),
                strMeal = recipeObject.optString("strMeal"),
                strMealThumb = recipeObject.optString("strMealThumb"),
                ingredients = null,
                strInstructions = null,
                strCategory = null
            )
            recipes.add(recipe)
        }
        return recipes
    }
}
