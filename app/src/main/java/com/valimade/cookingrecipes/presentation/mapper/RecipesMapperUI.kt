package com.valimade.cookingrecipes.presentation.mapper

import com.valimade.cookingrecipes.domain.model.Recipe
import com.valimade.cookingrecipes.presentation.model.RecipePreviewUI
import com.valimade.cookingrecipes.presentation.model.RecipeUI

object RecipesMapperUI {

    fun recipePreviewDomainToUI(recipe: Recipe): RecipePreviewUI {
        return RecipePreviewUI(
            id = recipe.id,
            title = recipe.title,
            image = recipe.image.orEmpty(),
            servings = recipe.servings ?: 0,
            readyInMinutes = recipe.readyInMinutes ?: 0,
            aggregateLikes = recipe.aggregateLikes ?: 0,
            annotation = createAnnotation(recipe),
        )
    }

    private fun createAnnotation(recipe: Recipe): String {
        val tags = listOfNotNull(
            if (recipe.vegetarian == true) "#vegetarian" else null,
            if (recipe.vegan == true) "#vegan" else null,
            if (recipe.glutenFree == true) "#glutenFree" else null,
            if (recipe.dairyFree == true) "#dairyFree" else null,
        ).joinToString(" ")

        val details = listOf(
            "Healthy: ${if (recipe.veryHealthy == true) "YES" else "NO"}",
            "Cheap: ${if (recipe.cheap == true) "YES" else "NO"}",
            "Popular: ${if (recipe.veryPopular == true) "YES" else "NO"}"
        ).joinToString("\n")

        return buildString {
            if (tags.isNotEmpty()) appendLine(tags)
            append(details)
        }
    }

    fun recipeDomainToUI(recipe: Recipe): RecipeUI {
        return RecipeUI(
            title = recipe.title,
            image = recipe.image.orEmpty(),
            ingredients = recipe.extendedIngredients?.map { it.original ?: it.name } ?: emptyList(),
            steps = recipe.analyzedInstructions?.flatMap { instruction ->
                    instruction.steps?.map { step -> step.step.orEmpty() } ?: emptyList()
                } ?: emptyList()
        )
    }

}