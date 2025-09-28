package com.valimade.cookingrecipes.presentation.model

data class RecipePreviewUI(
    val id: Int = 0,
    val title: String = "",
    val image: String = "",
    val servings: Int = 0,
    val readyInMinutes: Int =  0,
    val aggregateLikes: Int =  0,
    val annotation: String = "",
)