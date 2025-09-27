package com.valimade.cookingrecipes.data.repository

import com.valimade.cookingrecipes.data.mapper.RecipesMapperData
import com.valimade.cookingrecipes.data.model.RecipeData
import com.valimade.cookingrecipes.domain.model.Recipe
import com.valimade.cookingrecipes.domain.repository.RecipesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RecipesRepositoryImpl(
    private val httpClient: HttpClient,
    private val apiKey: String,
): RecipesRepository {
    override suspend fun getRandomRecipes(number: Int): Result<List<Recipe>> {
        return try {
            val response: List<RecipeData> = httpClient.get("/recipes/random") {
                parameter("number", number)
                parameter("apiKey", apiKey)
            }.body()

            val recipes = response.map { RecipesMapperData.recipeDataToDomain(it) }
            Result.success(recipes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}