package com.example.recipeappstep1.model

import java.io.Serializable

data class Category (val idCategory:Int, val strCategory:String, val strCategoryThumb:String, val strCategoryDescription:String) :
    Serializable {
}