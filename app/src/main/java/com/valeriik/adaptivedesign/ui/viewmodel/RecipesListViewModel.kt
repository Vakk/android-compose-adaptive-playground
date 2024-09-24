package com.valeriik.adaptivedesign.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valeriik.adaptivedesign.data.model.Recipe
import com.valeriik.adaptivedesign.data.repository.DummyRecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class RecipesListViewModel : ViewModel() {
    private val recipesRepository = DummyRecipesRepository()

    val recipesList = recipesRepository.fetchRecipes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun findRelatedRecipes(receipt: Recipe) = recipesRepository
        .findRelatedRecipes(receipt)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun fetchDetails(receiptId: String) = recipesRepository
        .fetchDetails(receiptId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}