package com.valimade.cookingrecipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.valimade.cookingrecipes.presentation.model.RecipeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeViewModel(): ViewModel() {
    private val _recipeState = MutableStateFlow(RecipeState())
    val recipeState = _recipeState.asStateFlow()

}