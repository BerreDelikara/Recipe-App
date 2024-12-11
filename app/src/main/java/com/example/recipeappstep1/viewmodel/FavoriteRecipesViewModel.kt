package com.example.recipeappstep1.viewmodel

import CallApi
import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.result.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappstep1.model.Recipe
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteRecipesViewModel : ViewModel() {
    val db = Firebase.firestore
    private var _recipes = MutableLiveData<MutableList<Recipe>>()
    val recipes: LiveData<MutableList<Recipe>> get() = _recipes


    fun saveFavorite(recipeId: String) {
        val userId = LoginViewModel().auth.currentUser?.uid ?: return
        val userFavoritesRef = db.collection("favorites").document(userId)

        userFavoritesRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                userFavoritesRef.update("favorites", FieldValue.arrayUnion(recipeId))
            } else {
                userFavoritesRef.set(mapOf("favorites" to listOf(recipeId)))
            }
        }.addOnFailureListener { e ->
            TODO()
        }
    }

    fun removeFavorite(recipeId: String) {
        val userId = LoginViewModel().auth.currentUser?.uid ?: return // Ensure user is logged in
        val userFavoritesRef = db.collection("favorites").document(userId)

        userFavoritesRef.update("favorites", FieldValue.arrayRemove(recipeId))
            .addOnFailureListener { e ->
                TODO()
            }
    }

    fun getFavorites() {
        LoginViewModel().auth.currentUser?.let { user ->
            db.collection("favorites").document(user.uid)
                .get()
                .addOnSuccessListener { result ->
                    val favorites = (result.get("favorites") as? List<String> ?: emptyList())
                    if (favorites.isNotEmpty()) {
                        viewModelScope.launch {
                            val recipes = withContext(Dispatchers.IO) {
                                CallApi().parseAllRecipeIds(favorites)
                            }
                            _recipes.value = recipes.toMutableList()
                        }
                    } else {
                        Log.d(TAG, "No favorites found.")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }
    }


}
