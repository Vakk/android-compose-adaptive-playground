package com.valeriik.adaptivedesign.data.repository

import com.valeriik.adaptivedesign.data.api.RecipesResponse
import com.valeriik.adaptivedesign.data.model.Recipe
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

/**
 * We will use a free API that allows an access to dummy recipes models.
 * Read the following to get details:
 * <a href="https://dummyjson.com/docs/recipes">Dummy JSON docs</a>
 */
class DummyRecipesRepository {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private var recipesCache = emptyList<Recipe>()

    fun fetchRecipes(): Flow<List<Recipe>> = flow {
        val response: RecipesResponse =
            client.get("https://dummyjson.com/recipes?select=name,ingredients,image,instructions,mealType")
                .body()
        recipesCache = response.recipes
        emit(response.recipes)
    }

    fun fetchDetails(receiptId: String) = flow {
        val id = receiptId.toInt()
        emit(recipesCache.first { it.id == id })
    }

    fun findRelatedRecipes(receipt: Recipe): Flow<List<Recipe>> = flow {
        val category = receipt.mealType.firstOrNull()
        if (category == null) {
            emit(emptyList())
        } else {
            val result = recipesCache.filter {
                it.mealType.contains(category)
            }
            emit(result)
        }
    }
}