package com.valimade.cookingrecipes.presentation.model

data class RecipePreviewState(
    val title: String = "",
    val image: String = "",
    val servings: Int = 0,
    val cookingMinutes: Int =  0,
    val aggregateLikes: Int =  0,
    val summary: String = "",
)