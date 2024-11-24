package com.example.recipeappstep1.model

import java.io.Serializable

class Ingredient(val name: String, val measure: String) : Serializable {

    override fun toString(): String {
        return "$measure $name"
    }
}
