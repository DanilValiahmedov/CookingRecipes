package com.valimade.cookingrecipes.presentation.model

data class RecipePreviewState(
    val isLoading: Boolean = true,
    val recipeList: List<RecipeUI> = emptyList(),
    val error: String? = null,
)