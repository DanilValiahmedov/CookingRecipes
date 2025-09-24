package com.valimade.cookingrecipes.domain.repository

import com.valimade.cookingrecipes.domain.model.Recipe

interface RecipesRepository {
    suspend fun getRandomRecipes(number: Int = 100) : Result<List<Recipe>>
}