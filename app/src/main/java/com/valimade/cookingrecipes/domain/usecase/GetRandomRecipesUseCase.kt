package com.valimade.cookingrecipes.domain.usecase

import com.valimade.cookingrecipes.domain.model.Recipe
import com.valimade.cookingrecipes.domain.repository.RecipesRepository

class GetRandomRecipesUseCase(private val repository: RecipesRepository) {
    suspend operator fun invoke(): Result<List<Recipe>> {
        return repository.getRandomRecipes()
    }
}