package com.valimade.cookingrecipes.presentation.mapper

import com.valimade.cookingrecipes.domain.model.Recipe
import com.valimade.cookingrecipes.presentation.model.RecipeUI

object RecipesMapperUI {
    fun recipeDomainToUI(recipe: Recipe): RecipeUI {
        return RecipeUI(
            title = recipe.title,
            image = recipe.image ?: "",
            servings = recipe.servings ?: 0,
            cookingMinutes = recipe.cookingMinutes ?: 0,
            aggregateLikes = recipe.aggregateLikes ?: 0,
            summary = recipe.summary ?: "",
        )
    }
}