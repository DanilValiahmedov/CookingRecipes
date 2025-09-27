package com.valimade.cookingrecipes.data.repository

import android.util.Log
import com.google.gson.Gson
import com.valimade.cookingrecipes.data.database.RecipeDao
import com.valimade.cookingrecipes.data.database.RecipeEntity
import com.valimade.cookingrecipes.data.mapper.RecipesMapperData
import com.valimade.cookingrecipes.data.model.RecipeData
import com.valimade.cookingrecipes.data.model.RecipesResponse
import com.valimade.cookingrecipes.domain.model.Recipe
import com.valimade.cookingrecipes.domain.repository.RecipesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RecipesRepositoryImpl(
    private val httpClient: HttpClient,
    private val recipeDao: RecipeDao,
    private val apiKey: String,
): RecipesRepository {
    override suspend fun getRandomRecipes(number: Int): Result<List<Recipe>> {
        return try {
            // Пытаемся получить данные из API
            val response: RecipesResponse = httpClient.get("/recipes/random") {
                parameter("number", number)
                parameter("apiKey", apiKey)
            }.body()

            Log.d("RecipesRepository", "Получено из API: ${response.recipes.size} рецептов")

            val recipes = response.recipes.map { recipeData ->
                Log.d("RecipesRepository", "Рецепт: $recipeData")
                val recipe = RecipesMapperData.recipeDataToDomain(recipeData)
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
            val gson = Gson()

            val recipeEntity = RecipeEntity(
                id = recipeData.id,
                title = recipeData.title,
                image = recipeData.image,
                imageType = recipeData.imageType,
                readyInMinutes = recipeData.readyInMinutes,
                servings = recipeData.servings,
                sourceUrl = recipeData.sourceUrl,
                vegetarian = recipeData.vegetarian,
                vegan = recipeData.vegan,
                glutenFree = recipeData.glutenFree,
                dairyFree = recipeData.dairyFree,
                veryHealthy = recipeData.veryHealthy,
                cheap = recipeData.cheap,
                veryPopular = recipeData.veryPopular,
                sustainable = recipeData.sustainable,
                lowFodmap = recipeData.lowFodmap,
                weightWatcherSmartPoints = recipeData.weightWatcherSmartPoints,
                gaps = recipeData.gaps,
                preparationMinutes = recipeData.preparationMinutes,
                cookingMinutes = recipeData.cookingMinutes,
                aggregateLikes = recipeData.aggregateLikes,
                healthScore = recipeData.healthScore,
                creditsText = recipeData.creditsText,
                license = recipeData.license,
                sourceName = recipeData.sourceName,
                pricePerServing = recipeData.pricePerServing,
                extendedIngredients = recipeData.extendedIngredients?.let {
                    gson.toJson(it)
                },
                summary = recipeData.summary,
                cuisines = recipeData.cuisines?.let {
                    gson.toJson(it)
                },
                dishTypes = recipeData.dishTypes?.let {
                    gson.toJson(it)
                },
                diets = recipeData.diets?.let {
                    gson.toJson(it)
                },
                occasions = recipeData.occasions?.let {
                    gson.toJson(it)
                },
                instructions = recipeData.instructions,
                analyzedInstructions = recipeData.analyzedInstructions?.let {
                    gson.toJson(it)
                },
                spoonacularScore = recipeData.spoonacularScore,
                spoonacularSourceUrl = recipeData.spoonacularSourceUrl
            )
            
            recipeDao.insert(recipeEntity)
            Log.d("RecipesRepository", "Рецепт сохранен: ${recipeData.title}")
        } catch (e: Exception) {
            Log.e("RecipesRepository", "Ошибка при сохранении: ${e.message}")
        }
    }

    private suspend fun getCachedRecipes(limit: Int): List<Recipe> {
        return try {
            val entities = recipeDao.getAll()
            val recipes = entities.take(limit).map { entity ->
                RecipesMapperData.recipeEntityToDomain(entity)
            }
            recipes
        } catch (e: Exception) {
            Log.e("RecipesRepository", "Ошибка получения из кэша: ${e.message}")
            emptyList()
        }
    }

}