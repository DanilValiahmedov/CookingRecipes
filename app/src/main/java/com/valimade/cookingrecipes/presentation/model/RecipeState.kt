package com.valimade.cookingrecipes.presentation.model

data class RecipeState(
    val isLoading: Boolean = true,
    val recipe: RecipeUI = RecipeUI(),
    val error: String? = null,
)