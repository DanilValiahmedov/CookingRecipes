package com.valimade.cookingrecipes.domain.usecase

import com.valimade.cookingrecipes.domain.model.Recipe
import com.valimade.cookingrecipes.domain.repository.RecipesRepository

class GetRecipeByIdUseCase(private val repository: RecipesRepository) {
    suspend operator fun invoke(id: Int): Result<Recipe> {
        return repository.getRecipeById(id)
    }
}