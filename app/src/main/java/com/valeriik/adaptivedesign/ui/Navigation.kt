package com.valeriik.adaptivedesign.ui

import kotlinx.serialization.Serializable

sealed class Navigation {
    @Serializable
    object RecipesList : Navigation()

    @Serializable
    data class RecipeDetails(
        val id: String,
        val title: String
    ) : Navigation()
}