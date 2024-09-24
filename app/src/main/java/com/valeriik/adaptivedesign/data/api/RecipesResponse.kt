package com.valeriik.adaptivedesign.data.api

import com.valeriik.adaptivedesign.data.model.Recipe
import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(
    val recipes: List<Recipe>
)