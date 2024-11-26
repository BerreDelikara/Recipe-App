import android.content.Context
import com.example.recipeappstep1.R
import com.example.recipeappstep1.model.Category
import com.example.recipeappstep1.model.Ingredient
import com.example.recipeappstep1.model.Recipe
import org.json.JSONObject

class Parser(private val context: Context) {

    fun parseRecipes(categoryName: String): List<Recipe> {

        val jsonString = context.resources.openRawResource(context.resources.getIdentifier(categoryName, "raw", context.packageName)).bufferedReader().use {
            it.readText() }


        val mealsArray = JSONObject(jsonString).getJSONArray("meals")
        val recipes = mutableListOf<Recipe>()


        for (i in 0 until mealsArray.length()) {
            val mealObject = mealsArray.getJSONObject(i)
            val ingredientList = mutableListOf<Ingredient>()
            for (j in 1..20) {
                val ingName = mealObject.optString("strIngredient$j", "").trim()
                val measure = mealObject.optString("strMeasure$j", "").trim()
                if (ingName.isNotEmpty()) {
                    ingredientList.add(Ingredient(ingName, measure))
                }
            }

            val recipe = Recipe(
                idMeal = mealObject.getInt("idMeal"),
                strMeal = mealObject.getString("strMeal"),
                strCategory = mealObject.getString("strCategory"),
                strInstructions = mealObject.getString("strInstructions"),
                strMealThumb = mealObject.getString("strMealThumb"),
                ingredients = ingredientList
            )
            recipes.add(recipe)
        }

        return recipes
    }

    fun parseCategories(): List<Category> {
        val jsonString = context.resources.openRawResource(R.raw.categories).bufferedReader().use {
            it.readText() }

        val categoryList = mutableListOf<Category>()
        val categoriesArray = JSONObject(jsonString).getJSONArray("categories")

        for (i in 0 until categoriesArray.length()) {
            val categoryObject = categoriesArray.getJSONObject(i)

            val category = Category(idCategory = categoryObject.getInt("idCategory"),
                strCategory = categoryObject.getString("strCategory"),
                strCategoryThumb = categoryObject.getString("strCategoryThumb"),
                strCategoryDescription = categoryObject.getString("strCategoryDescription"))

            categoryList.add(category)
        }
        return categoryList
    }
}
