package com.valeriik.adaptivedesign.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealType: List<String>,
    val image: String
)