package com.valimade.cookingrecipes.data.repository

import android.util.Log
import com.valimade.cookingrecipes.data.database.RecipeDao
import com.valimade.cookingrecipes.data.mapper.RecipesMapperData
import com.valimade.cookingrecipes.data.model.RecipeData
import com.valimade.cookingrecipes.data.model.RecipeNotFound
import com.valimade.cookingrecipes.data.model.RecipesResponse
import com.valimade.cookingrecipes.domain.model.Recipe
import com.valimade.cookingrecipes.domain.repository.RecipesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RecipesRepositoryImpl(
    private val httpClient: HttpClient,
    private val apiKey: String,
    private val dao: RecipeDao,
    private val mapper: RecipesMapperData,
): RecipesRepository {
    override suspend fun getRandomRecipes(number: Int): Result<List<Recipe>> {
        return try {
            val response: RecipesResponse = httpClient.get("/recipes/random") {
                parameter("number", number)
                parameter("apiKey", apiKey)
            }.body()

            Log.d("RecipesRepository", "Получено из API: ${response.recipes.size} рецептов")

            val recipes = response.recipes.map { recipeData ->
                Log.d("RecipesRepository", "Рецепт: $recipeData")
                val recipe = mapper.recipeDataToDomain(recipeData)
                saveRecipeToDatabase(recipeData)
                recipe
            }

            Result.success(recipes)
        } catch (e: Exception) {
            Log.e("RecipesRepository", "Ошибка API: ${e.message}")
            try {
                val cachedRecipes = getCachedRecipes(number)
                if (cachedRecipes.isNotEmpty()) {
                    Log.d("RecipesRepository", "Получено из кэша: ${cachedRecipes.size} рецептов")
                    Result.success(cachedRecipes)
                } else {
                    Log.w("RecipesRepository", "Кэш пуст")
                    Result.failure(e)
                }
            } catch (cacheException: Exception) {
                Log.e("RecipesRepository", "Ошибка кэша: ${cacheException.message}")
                Result.failure(e)
            }
        }
    }

    private suspend fun saveRecipeToDatabase(recipeData: RecipeData) {
        try {
            val recipeEntity = mapper.recipeDataToEntity(recipeData)

            dao.insert(recipeEntity)
            Log.d("RecipesRepository", "Рецепт сохранен: ${recipeData.title}")
        } catch (e: Exception) {
            Log.e("RecipesRepository", "Ошибка при сохранении: ${e.message}")
        }
    }

    private suspend fun getCachedRecipes(limit: Int): List<Recipe> {
        return try {
            val entities = dao.getAll()
            val recipes = entities.take(limit).map { entity ->
                mapper.recipeEntityToDomain(entity)
            }
            recipes
        } catch (e: Exception) {
            Log.e("RecipesRepository", "Ошибка получения из кэша: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getRecipeById(id: Int): Result<Recipe> {
        return try {
            val entity = dao.getById(id)
            val recipe = if(entity != null) {
                mapper.recipeEntityToDomain(entity)
            } else {
                throw RecipeNotFound()
            }
            Result.success(recipe)
        } catch (e: Exception) {
            Log.e("RecipesRepository", "Ошибка получения конкретного рецепта: ${e.message}")
            Result.failure(e)
        }
    }

}