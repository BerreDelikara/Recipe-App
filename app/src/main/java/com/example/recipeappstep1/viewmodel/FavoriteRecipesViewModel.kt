package com.example.recipeappstep1.viewmodel

import CallApi
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipeappstep1.model.Recipe
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

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
        var favorites = mutableListOf<String>()
        LoginViewModel().auth.currentUser?.let {
            db.collection("favorites").document(it.uid)
                .get()
                .addOnSuccessListener { result ->
                    favorites = (result.get("favorites") as? List<String> ?: emptyList()).toMutableList()
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }
        _recipes.value = CallApi().parseAllRecipeIds(favorites).toMutableList()
    }
}
