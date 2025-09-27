package com.valimade.cookingrecipes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(
    val recipes: List<RecipeData>
)