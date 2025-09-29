package com.valimade.cookingrecipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valimade.cookingrecipes.domain.usecase.GetRecipeByIdUseCase
import com.valimade.cookingrecipes.presentation.mapper.RecipesMapperUI
import com.valimade.cookingrecipes.presentation.model.RecipeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
): ViewModel() {
    private val _recipeState = MutableStateFlow(RecipeState())
    val recipeState = _recipeState.asStateFlow()

    fun getRecipeById(id: Int) {
        viewModelScope.launch {
            getRecipeByIdUseCase(id)
                .onSuccess { recipeDomain ->
                    _recipeState.update {
                        it.copy(
                            isLoading = false,
                            recipe = RecipesMapperUI.recipeDomainToUI(recipeDomain),
                            error = null,
                        )
                    }
                }
                .onFailure { failure ->
                    _recipeState.update {
                        it.copy(
                            isLoading = false,
                            error = failure.message,
                        )
                    }
                }
        }
    }

}