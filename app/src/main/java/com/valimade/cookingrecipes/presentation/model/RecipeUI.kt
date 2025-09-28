package com.valimade.cookingrecipes.presentation.model

data class RecipeUI(
    val title: String = "",
    val image: String = "",
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList(),
)
